package it.apulia.esercitazione1REST.AccessManager;

import it.apulia.esercitazione1REST.UserMNG.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean verifyEmail(String email) {
        return this.userRepository.findUtenteByEmail(email).isPresent();
    }

    public boolean verifyPasswordUtente(LoginDTO loginDTO) {
        //controlla che l'utente sia presente all'interno della hashmap
        if(verifyEmail(loginDTO.getEmail()))
        {
            //verifica che le password siano uguali
            //if(this.similDB.get(loginDTO.getEmail()).getPassword().equals(loginDTO.getPassword()))
            if(this.userRepository.findUtenteByEmail(loginDTO.getEmail()).get().getPassword().equals(loginDTO.getPassword()))
            {
                return true;
            }
            return false; //password errata
        }
        return false; //l'utente non è presente all'interno del sistema
    }
}
