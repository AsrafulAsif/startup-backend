package com.example.startup.startup.fileSystem.response;

import com.example.startup.startup.model.SimpleResponseRest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class ImageUrlResponseRest extends SimpleResponseRest {
    private ImageUrlResponse imageUrlResponse;
    @Data
    @Builder
    static
    public class ImageUrlResponse{
         private String imageUrl;
    }
}
