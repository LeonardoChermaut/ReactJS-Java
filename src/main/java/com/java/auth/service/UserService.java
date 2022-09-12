package com.java.auth.service;
import com.java.auth.dto.CreateUserDto;
import com.java.auth.dto.UpdateUserDto;
import com.java.auth.dto.UserDto;
import com.java.auth.exception.UserNotExistentException;
import com.java.auth.exception.UserNotFoundException;
import com.java.auth.model.UserModel;
import com.java.auth.repository.UserRepository;
import com.java.auth.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

	private ModelMapper mapper = new ModelMapper();
	@Autowired
	private UserRepository repository;

	@Autowired
	private UserUtil util;

	@Bean
	public PasswordEncoder getPassEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Transactional
	public Long save(CreateUserDto dto) throws Exception {
		UserModel model = mapper.map(dto, UserModel.class);
		model.setSenha(getPassEncoder().encode(dto.getSenha()));


		if(repository.findByEmail(model.getEmail()).isPresent()) {
			throw new Exception("Email já existente");
		}
		return repository.save(model).getId();
	}

	@Transactional
	public void update(long id, UpdateUserDto dto) throws UserNotFoundException {
		UserModel model = this.repository.findById(id).orElseThrow(UserNotFoundException::new);
		model.setNome(dto.getNome());
		model.setEmail(dto.getEmail());
		model.setSobrenome(dto.getSobrenome());
		repository.save(model);
	}

	@Transactional
	public void remove(long id) throws UserNotFoundException {
		objectOrThrow(id);
		repository.deleteById(id);
	}

	@Transactional
	public List<UserDto> allObjects() {
		return repository.findAll().stream().map(model -> mapper.map(model, UserDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public UserDto findById(long id) throws UserNotFoundException {
		return objectOrThrow(id);
	}

	@Transactional
	private UserDto objectOrThrow(long id) throws UserNotFoundException {
		return repository.findById(id).map(model -> mapper.map(model, UserDto.class))
				.orElseThrow(UserNotFoundException::new);
	}

	public Optional<UserModel> contex() {
		UserModel model = util.getUsuario();
		return repository.findByNome(model.getNome());
	}

}