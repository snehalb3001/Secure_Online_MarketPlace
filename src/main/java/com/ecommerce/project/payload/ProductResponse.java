package com.ecommerce.project.payload;


import com.ecommerce.project.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
   private List<ProductDTO> content;
   private Integer pageNumber;
   private Integer pageSize;
   private Long totalElements;
   private Integer totalpages;
   private boolean lastPage;

}
