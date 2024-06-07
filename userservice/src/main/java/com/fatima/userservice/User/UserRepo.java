package com.fatima.userservice.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> { 

    List<User> findByFirstname(String firstname);
    List<User> findByLastname(String lastname);

    @Query("SELECT u FROM User u WHERE CONCAT(u.firstname, ' ', u.lastname) = ?1")
    List<User> findByFullname(String fullname);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    // List <User> findByGetName(String name);
 
// User findUserByJwtUser(String jwt);


    // Optional<User> findByUserName(String userName);

    @Query("SELECT u.friends FROM User u WHERE u.id = :id")
    Set<User> getFriends(Long id);
    @Query("SELECT COUNT(f) FROM User u JOIN u.friends f WHERE u.id = :userId")
    Long countFriends(Long userId);
    @Query("SELECT u FROM User u WHERE u.id NOT IN (SELECT f.id FROM User user JOIN user.friends f WHERE user.id = :userId) AND u.id != :userId")
    List<User> findSuggestedFriends(@Param("userId") Long userId);

    // @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(concat('%', :searchTerm, '%')) OR LOWER(u.firstname) LIKE LOWER(concat('%', :searchTerm, '%')) OR LOWER(u.lastname) LIKE LOWER(concat('%', :searchTerm, '%'))")
    // List<User> searchUsers(@Param("searchTerm") String searchTerm);

    
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(concat('%', :searchTerm, '%')) OR LOWER(u.firstname) LIKE LOWER(concat('%', :searchTerm, '%')) OR LOWER(u.lastname) LIKE LOWER(concat('%', :searchTerm, '%'))")
    List<User> searchUsers(@Param("searchTerm") String searchTerm);

  
}
