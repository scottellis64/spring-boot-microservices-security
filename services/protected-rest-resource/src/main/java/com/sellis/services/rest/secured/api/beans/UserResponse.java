package com.sellis.services.rest.secured.api.beans;

import lombok.Data;

@Data
public class UserResponse {
   private String status;

   public UserResponse(String status) {
      this.status = status;
   }
}
