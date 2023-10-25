package pl.benzo.enzo.knowmeuploader.implementation.soap;


import jakarta.activation.DataHandler;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "file",
        "photoId"
})
@XmlRootElement(name = "uploadImageRequest")
@Getter
@Setter
public class UploadImageRequest {
    @XmlElement(required = true)
    protected DataHandler file;
    @XmlElement(required = true)
    protected String photoId;
}