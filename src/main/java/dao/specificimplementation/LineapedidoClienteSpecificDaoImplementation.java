package dao.specificimplementation;

import bean.helper.MetaBeanHelper;
import bean.specificimplementation.TiendaSpecificBeanImplementation;
import bean.specificimplementation.TipousuarioSpecificBeanImplementation;
import bean.specificimplementation.UsuarioSpecificBeanImplementation;
import java.sql.Connection;

public class LineapedidoClienteSpecificDaoImplementation extends LineapedidoSpecificDaoImplementation {

    private Integer idUsuario = 0;
    private Integer idTienda = 0;

    public LineapedidoClienteSpecificDaoImplementation(Connection oPooledConnection, MetaBeanHelper oPuserBean_security, String strWhere) throws Exception {
        super(oPooledConnection, oPuserBean_security, strWhere);

        UsuarioSpecificBeanImplementation oUsuario = (UsuarioSpecificBeanImplementation) oPuserBean_security.getBean();
        idUsuario = oUsuario.getId();
        MetaBeanHelper ombhTipousuario = (MetaBeanHelper) oUsuario.getObj_tipousuario();
        TipousuarioSpecificBeanImplementation oTipousuario = (TipousuarioSpecificBeanImplementation) ombhTipousuario.getBean();
        if (oTipousuario.getId() == 3) {
            String strSQLini = "";

            TiendaSpecificBeanImplementation oTienda = (TiendaSpecificBeanImplementation) oUsuario.getObj_tienda().getBean();
            idTienda = oTienda.getId();
            strSQLini = "FROM lineapedido where 1=1 "
                    + " AND  id IN (SELECT lp.id FROM pedido p, lineapedido lp, usuario u"
                    + " WHERE p.id_usuario=" + idUsuario
                    + " AND lp.id_pedido=p.id) ";

            strSQL = "SELECT * " + strSQLini;
            strCountSQL = "SELECT COUNT(*) " + strSQLini;
            if (strWhere != null) {
                strSQL += " " + strWhere + " ";
                strCountSQL += " " + strWhere + " ";
            }
        }

    }
}