package com.hosu.accountbook.service;import com.hosu.accountbook.domain.UserAccount;import com.hosu.accountbook.dto.UserAccountDTO;import com.hosu.accountbook.repository.UserRepository;import jakarta.persistence.EntityNotFoundException;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;@RequiredArgsConstructor@Service@Transactional@Slf4jpublic class UserAccountService {    private final UserRepository userRepository;    @Transactional(readOnly = true)    public List<UserAccountDTO> searchUser(){        return userRepository.findAll().stream().map(UserAccountDTO::from).toList();    }    public void saveUser(UserAccountDTO dto){        userRepository.save(dto.toEntity());    }    @Transactional(readOnly = true)    public Long loginUser(UserAccountDTO dto){        UserAccount userAccount = userRepository.findByEmail(dto.email())                .orElseThrow(() -> new EntityNotFoundException("찾는 유저가 없습니다."));        if(!dto.password().equals(userAccount.getPassword())){            throw new RuntimeException();        }        return userAccount.getId();    }    public boolean withdraw(UserAccountDTO dto){        UserAccount userAccount = userRepository.findByEmail(dto.email())                .orElseThrow(() -> new EntityNotFoundException("찾는 유저가 없습니다."));        if(!dto.password().equals(userAccount.getPassword())){            throw new RuntimeException();        }        userRepository.deleteById(userAccount.getId());        userRepository.flush();        return true;    }}