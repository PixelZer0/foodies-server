package bean.specificimplementation;

import com.google.gson.annotations.Expose;
import bean.genericimplementation.TableGenericBeanImplementation;
import bean.meta.publicinterface.MetaObjectBeanInterface;
import bean.meta.publicinterface.MetaPropertyBeanInterface;
import helper.EnumHelper;
import helper.constant.RegexConstants;
import java.util.Date;

@MetaObjectBeanInterface(
        TableName = "ticket",
        SingularDescription = "Ticket del pedido",
        PluralDescription = "Tickets de los pedidos",
        Icon = "fa fa-list",
        Type = EnumHelper.SourceType.Table
)
public class TicketSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.ticket",
            LongName = "Fecha de ticket",
            Description = "Fecha de expedición del ticket",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = true,
            IsVisible = true
    )
    private Date fecha_alta;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Iva",
            LongName = "Iva",
            Description = "Iva del producto",
            Type = EnumHelper.FieldType.Integer,
            IsRequired = true,
            IsForeignKeyDescriptor = true
    )
    private Integer iva = 0;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tipo",
            LongName = "Tipo de usuario",
            Description = "Tipo de usuario dentro del sistema",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";

    public TicketSpecificBeanImplementation() {
    }

    public TicketSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
