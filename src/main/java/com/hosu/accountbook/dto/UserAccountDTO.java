package com.hosu.accountbook.dto;import com.hosu.accountbook.domain.UserAccount;import java.time.LocalDateTime;public record UserAccountDTO(    Long id,    String email,    String password,    String userName,    LocalDateTime createdAt,    LocalDateTime modifiedAt) {    public static UserAccountDTO from(UserAccount entity){        return new UserAccountDTO(                entity.getId(),                entity.getEmail(),                entity.getPassword(),                entity.getUserName(),                entity.getCreatedAt(),                entity.getModifiedAt()        );    }    public static UserAccountDTO of(String email, String password, String userName){        return new UserAccountDTO(null, email, password, userName, null, null);    }    public static UserAccountDTO of(String email, String password){        return new UserAccountDTO(null, email, password, null, null, null);    }    public UserAccount toEntity(){        return UserAccount.of(email, password, userName);    }}