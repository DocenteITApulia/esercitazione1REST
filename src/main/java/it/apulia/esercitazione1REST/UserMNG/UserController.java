package it.apulia.esercitazione1REST.UserMNG;

import it.apulia.esercitazione1REST.AccessManager.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "usermng")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

	
	@GetMapping("/orario")
	  public Date date() {
	    return new Date();
	  }

		
	@PostMapping("/register")
	public void addUser(
			@RequestBody @Valid UtenteDTO formutente) {
		

		if ( this.userService.verifyEmail(formutente.getEmail()) || !this.userService.verificaPassword(formutente.getPassword(), formutente.getVpassword())) {
			//TODO gestire errore
		}
		this.userService.saveUser(formutente);
	}

	@GetMapping("/users")
	public List<Utente> getUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping(value = "/users/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Utente getUser(@PathVariable String email){
		return this.userService.getUser(email);
	}

	@PutMapping("/users/{email}")
	public boolean updateUser(@RequestBody Utente user) {
		return this.userService.updateUser(user);
	}

	@DeleteMapping("/users/{email}")
	public void deleteUser(@PathVariable String email){
		this.userService.deleteUser(email);
	}


	
}
