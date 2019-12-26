package com.sellis.services.rest.secured.api.beans;

import lombok.Data;

@Data
public class AdminResponse {
   private String status;

   public AdminResponse(String status) {
      this.status = status;
   }
}
