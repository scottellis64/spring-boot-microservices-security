package com.sellis.services.rest.secured.api.resource;

import com.sellis.services.rest.secured.api.beans.AdminResponse;
import com.sellis.services.rest.secured.api.beans.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/secured")
public class ProtectedRestResource {
   /**
    * Eventually the user will need to have a user role to access this resource.
    * For now this is not secured in any way.
    *
    * @return user-accessible status
    */
   @GetMapping(path = "user")
   public UserResponse getUserRoleData() {
      return new UserResponse("ok");
   }

   /**
    * Eventually the user will need to have an admin role to access this resource.
    * For now this is not secured in any way.
    *
    * @return admin-only-accessible status
    */
   @GetMapping(path = "admin")
   public AdminResponse getAdminRoleData() {
      return new AdminResponse("ok");
   }
}
