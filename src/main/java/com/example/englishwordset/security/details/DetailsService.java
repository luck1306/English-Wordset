package com.example.englishwordset.security.details;

import com.example.englishwordset.entity.user.User;
import com.example.englishwordset.entity.user.UserRepository;
import com.example.englishwordset.exceptioin.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

//    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findById(username).orElseThrow(()-> UserNotFoundException.EXCEPTION);
//        return new Details(user);
        return new Details(new User("", ""));
    }
}
