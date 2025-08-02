package com.example.cheque.dto.basic;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MainResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = -4468813687101812411L;

	private Long id;

	private LocalDateTime createdAt;

	private LocalDateTime lastUpdate;

}
