package io.garden.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.garden.project.model.entity.Payment;
import io.garden.project.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Payment", tags = {"Payment"})
public class PaymentRestController {
	
	@Autowired
	private PaymentService service;
	
	@GetMapping("/clients/{clientId}/payments/{paymentId}")
	@ApiOperation(value = "Find Payment by client Id and Payment Id", notes = "Provide an id  to look up specific Payment from Api", response = Payment.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Api"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})	
	public Optional<Payment> findPaymentByClientIdAndId(@PathVariable Long clientId, @PathVariable Long paymentId) {
		return service.findPaymentByClientIdAndPaymentId(clientId, paymentId);		
	}
	
	@GetMapping("/clients/{clientId}/payments")
	@ApiOperation(value = "Find all Payments By Client Id", notes = "Returns all Payments from Api", response = Payment.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Payments"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public Page<Payment> findAllPaymentsByClientId(@PathVariable Long clientId, Pageable pageable) {
		return service.findAllPaymentsByClientId(clientId, pageable);
	}
	
//	@PostMapping
//	@ApiOperation(value = "Create a new Payment", notes = "Returns new Payment created and saved into Api", response = Payment.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully Saved Payment"),
//			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
//			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
//			@ApiResponse(code = 404, message = "The resource  was not found")
//	})
//	public ResponseEntity<Payment> create(@RequestBody(required = true) Payment office) {
//		
//		Payment paymentCreated = service.create(office);
//		
//		return ResponseEntity.ok(paymentCreated);
//	}
	
//	@PutMapping("/{id}")
//	@ApiOperation(value = "Update an existing Payment", notes = "Returns Payment updated and saved into Api", response = Payment.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully Updated Payment"),
//			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
//			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
//			@ApiResponse(code = 404, message = "The resource  was not found")
//	})
//	public ResponseEntity<Payment> update(@RequestBody(required = true) Payment office, @PathVariable Long id){
//		Optional<Payment> paymentOptional = service.findPaymentByClientIdAndPaymentId(id);
//
//		if (!paymentOptional.isPresent())
//			return ResponseEntity.notFound().build();
//
//		office.setId(id);
//		
//		Payment  paymentUpdated = service.update(office);
//
//		return ResponseEntity.ok(paymentUpdated);
//	}
	
//	@DeleteMapping("/{id}")
//	@ApiOperation(value = "Delete an existing Payment", notes = "Returns nothing after operaton", response = Payment.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully"),
//			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
//			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
//			@ApiResponse(code = 404, message = "The resource  was not found")
//	})
//	public ResponseEntity<Optional<Payment>> delete(@PathVariable Long id){
//		Optional<Payment> paymentOptional = service.findPaymentByClientIdAndPaymentId(id);
//
//		if (!paymentOptional.isPresent())
//			return ResponseEntity.notFound().build();
//
//		service.delete(id);
//		return ResponseEntity.ok(paymentOptional);
//	}

}
