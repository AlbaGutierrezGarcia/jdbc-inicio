package modelos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class GamaProducto {
    private String gama;
    private String descripcion_texto;
    private String descripcion_html;
    private String imagen;

}