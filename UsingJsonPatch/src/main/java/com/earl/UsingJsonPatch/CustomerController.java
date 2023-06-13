package com.earl.UsingJsonPatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerServiceI customerService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public CustomerController(CustomerServiceI customerService) {
		this.customerService = customerService;
	}

	@GetMapping(path = "")
	List<Customer> findAllCustomer() {
		return customerService.finalAllCustomers();
	}

	@GetMapping(path = "/{id}")
	Customer findCustomer(@PathVariable String id) {
		return customerService.findCustomer(id).orElseThrow(CustomerNotFoundException::new);
	}

	@PatchMapping(path = "/{id}"/* , consumes = "application/json-patch+json" */)
	public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody JsonPatch patch) {
		try {
			Customer customer = customerService.findCustomer(id).orElseThrow(CustomerNotFoundException::new);
			Customer customerPatched = applyPatchToCustomer(patch, customer);
			customerService.updateCustomer(customerPatched);
			return ResponseEntity.ok(customerPatched);
		} catch (JsonPatchException | JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (CustomerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	private Customer applyPatchToCustomer(JsonPatch patch, Customer targetCustomer)
			throws JsonPatchException, JsonProcessingException {
		/**
		 * Unclear how to limit "op". Unclear how to limit the fields (no "/id")
		 */
		JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
		return objectMapper.treeToValue(patched, Customer.class);
	}
}
