package com.fatima.postservice;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatima.postservice.Comment.Comment;
import com.fatima.postservice.Like.Like;
import com.fatima.postservice.Post.Post;
import com.fatima.postservice.Share.Share;


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstname="unKnown";
    private String lastname="user";
    private String mobile;
    private String email;
    private int age;
    private String username;
    private Gender gender;
    private String bio;
    private String location;

    @Transient 
    private String fullname;
    private String image;
    
    private LocalDate dateofbirth;
    private boolean accountIsPrivate=false; 
    private String backgroudimage;
    
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    private boolean dark=false; 

  
    private List<Post> posts;
  
  
    private List<Comment> comments;
 
    private List<Like> likes;

    private List<Share> shares;

  
    private Set<User> friends;
    
    public User() {
    }

    public User(String username, String email, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstName) {
        if (firstName != null) { 
            this.fullname = this.firstname = this.firstname + " " + this.lastname;
            this.firstname = firstName;}
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
if(lastName!=null){
        this.fullname = this.firstname + " " + this.lastname;
        this.lastname = lastName;}
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        if (dateofbirth == null) {
            return 0;
        } else {
            this.age = (int) (LocalDate.now().getYear() - getDateofbirth().getYear());
           
            return age;
        }
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFullname() {
        this.fullname = this.firstname = this.firstname + " " + this.lastname;
        return fullname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateOfBirth) {
        this.dateofbirth = dateOfBirth;
    }

    public String getBackgroudimage() {
        return backgroudimage;
    }

    public void setBackgroudimage(String backgroudImage) {
        this.backgroudimage = backgroudImage;
    }

    // public boolean isFriend() {
    //     return isfriend;
    // }

    // public void setFriend(boolean isFriend) {
    //     this.isfriend = isFriend;
    // }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // public ArrayList<Role> getRole() {
    //     if (role == null) {
    //         role = new ArrayList<>(); 
    //         role.add(Role.USER); 
    //     }
       
    //     return role;
        
    // }
    

    // public void setRole(ArrayList<Role> role) {
    //     this.role = role;
    // }


    public boolean getAccountIsPrivate() {
        return accountIsPrivate;
    }


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void setAccountIsPrivate(boolean accountIsPrivate) {
        this.accountIsPrivate = accountIsPrivate;
    }



    
    public Long getuserid() {
       return this.id;
    }


    public boolean isDark() {
        return dark;
    }


    public void setDark(boolean dark) {
        this.dark = dark;
    }


    public User( String firstname, String lastname , String email,String location,String bio,String mobile, Gender gender,LocalDate dateofbirth) {
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.bio = bio;
        this.location = location;
        this.dateofbirth = dateofbirth;
        this.firstname= firstname;
        this.lastname= lastname;
    }

   
}
