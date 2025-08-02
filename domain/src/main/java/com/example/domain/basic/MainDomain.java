package com.example.domain.basic;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class MainDomain implements Serializable {

	@Serial
	private static final long serialVersionUID = 1925333199368033050L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

}
