package com.evolv.care.app.controller;

import com.evolv.care.app.dto.UserInfo;
import com.evolv.care.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user in the system.
     * Only users with role SUPER_ADMIN can access this API.
     *
     * @param userInfo the information of the user to create
     * @return the created user with its details
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
        System.out.println("Attempting to Create User: "+ userInfo.getUserName());
        return ResponseEntity.ok(userService.createUser(userInfo));
    }

    /**
     * Retrieves a list of all users.
     * Accessible by users with roles SUPER_ADMIN, ADMIN, or USER.
     *
     * @return list of all users
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves the details of a specific user by ID.
     * Accessible by users with roles SUPER_ADMIN, ADMIN, or USER.
     *
     * @param id the ID of the user
     * @return the user information for the given ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Voids (soft-deletes) a user by setting a flag instead of removing the record.
     * Only users with role SUPER_ADMIN or ADMIN can access this API.
     *
     * @param id the ID of the user to void
     * @return no content if voided successfully, not found if user does not exist
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Void> voidUser(@PathVariable Long id) {
        int updated = userService.voidUserById(id);

        if (updated > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

