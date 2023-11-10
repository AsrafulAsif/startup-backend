package com.example.startup.startup.fileSystem.response;

import com.example.startup.startup.model.SimpleResponseRest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class ImageUrlListResponseRest extends SimpleResponseRest {
     List<ImageUrlResponseRest.ImageUrlResponse> imageUrlList;
}
