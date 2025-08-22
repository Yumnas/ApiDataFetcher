package com.example.apidatafetcher.Authentication;

import com.example.apidatafetcher.Employee.entity.Employee;
import com.example.apidatafetcher.Employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServices implements UserDetailsService {
        private final EmployeeRepository employeeRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Employee employee = employeeRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

            return org.springframework.security.core.userdetails.User
                    .withUsername(employee.getEmail())
                    .password(employee.getPassword())
                    .authorities(Collections.emptyList())
                    .build();
        }
    }

