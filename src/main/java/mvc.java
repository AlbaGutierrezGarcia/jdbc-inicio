
import controladores.MenuPrincipal;

import java.sql.SQLException;

public class mvc {

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        MenuPrincipal menu = new MenuPrincipal();
        menu.presentarMenuPrincipal();
    }
}
