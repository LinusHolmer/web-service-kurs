package com.linusholmer.lektion_4.controller;

import com.linusholmer.lektion_4.model.CustomUser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController // Contains: @ResponseBody & @Controller
// @Controller // Is a specialized @Component for classpath Scanning & Mappings
// @Component // Enables naming of stereotype annotations (Service, repository, restcontroller), activation of classpath scanning
// @ResponseBody // Sends a result (JSON/HTML)
@RequestMapping(value ="/v1/user")
public class UserController {

    // Debugging User List (Database users)
    List<CustomUser> userList = new ArrayList<>(
            List.of(
                    new CustomUser("Linus","test", true),
                    new CustomUser("Michael","123", true),
                    new CustomUser("Jackson","abc", true),
                    new CustomUser("Gilbert","best", true),
                    new CustomUser("Gilbert","1234", false)
            )
    );

    @GetMapping("/")
    public List<CustomUser> test() {

        return userList;
    }

    // RequestParam vs Pathvariable
    // RequestParam: When the value is optional
    // Example: localhost:8080/products/shirts?color=blue
    // PathVariable: When the value is required to proceed
    // Example: localhost:8080/auth/userId/24198/processPayment

    // RequestParam does NOT require the symbol: '{}' (See PathVariable for reference)
    // localhost:8080/v1/user/find?username=Linus
    @GetMapping("/find")
    public List<CustomUser> findUser(
            @RequestParam(value ="username", defaultValue = "Linus")
            String username
    ) {

        List<CustomUser> foundUserList = userList.stream()
                .filter(i -> Objects.equals(i.username(), username))
                .toList();

        return foundUserList;
    }


    @DeleteMapping("/{username}")
    public CustomUser deleteUser(@PathVariable("username") String username) {

        CustomUser customUserToBeDeleted;

        for(int i = 0; i < userList.size(); i++) {
            if(Objects.equals(userList.get(i).username(), username)) {
                customUserToBeDeleted = userList.get(i);
                userList.remove(customUserToBeDeleted);

                return customUserToBeDeleted;
            }
        }


        return null;
    }

}
