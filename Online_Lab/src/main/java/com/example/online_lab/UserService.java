package com.example.online_lab;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String registerUser(String phoneNumber, String password, String userName) {
        // 檢查手機號碼是否已註冊
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "號碼已註冊");
        }

        // 生成 salt 並 hash 密碼
        String salt = BCrypt.gensalt();
        String passwordHash = BCrypt.hashpw(password, salt);

        // 建立使用者
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setPasswordHash(passwordHash);
        user.setSalt(salt);
        user.setUserName(userName);
        user.setRegistrationTime(LocalDateTime.now());

        // 儲存使用者
        userRepository.save(user);

        return "註冊成功";
    }

    public String loginUser(String phoneNumber, String password) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "使用者不存在");
        }

        User user = optionalUser.get();
        if (BCrypt.checkpw(password, user.getPasswordHash())) {
            // 密碼驗證成功，生成 JWT token（有效期 30 分鐘）
            return JwtUtils.generateToken(phoneNumber, 30); // 30 分鐘過期
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密碼錯誤");
        }
    }
}
