package pl.codeprime.response;

import java.util.Arrays;
import java.util.Objects;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public enum ResponseEnum {

	BAD_REQUEST(Status.BAD_REQUEST), 
	NO_CONTENT(Status.NO_CONTENT), 
	OK(Status.OK),
	FORBIDDEN(Status.FORBIDDEN), 
	GATEWAY_TIMEOUT(Status.GATEWAY_TIMEOUT), 
	NOT_FOUND(Status.NOT_FOUND), 
	REQUEST_TIMEOUT(Status.REQUEST_TIMEOUT), 
	SERVICE_UNAVAILABLE(Status.SERVICE_UNAVAILABLE), 
	UNAUTHORIZED(Status.UNAUTHORIZED),
	TOO_MANY_REQUESTS(Status.TOO_MANY_REQUESTS);
	
	private Status status;
	
	public Response buildResponse() {
		return Response.status(getStatus()).build();
	}

	private ResponseEnum(Status status) {
		this.status = status;
	}


	public Status getStatus() {
		return status;
	}

	public int getStatusCode() {
		return getStatus().getStatusCode();
	}
	
	public static ResponseEnum getResponseByStatus(Status status) {

		ResponseEnum obtainedResponse = Arrays.asList(ResponseEnum.values())
										.parallelStream()
											.filter(e -> e.getStatus().equals(status))
												.findFirst()
													.filter(Objects::nonNull)
														.orElse(NO_CONTENT);
		
		return obtainedResponse;
					
	}
	
	public static boolean isOK(Status type) {
		boolean equals = ResponseEnum.OK.getStatus().equals(type);
		
		return equals;
	}
	
}
