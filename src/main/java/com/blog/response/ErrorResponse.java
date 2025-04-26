package com.blog.response;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message) {

}
