package pl.benzo.enzo.kmservicedto.uploader;
import lombok.Getter;
import lombok.Setter;

import jakarta.activation.DataHandler;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
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