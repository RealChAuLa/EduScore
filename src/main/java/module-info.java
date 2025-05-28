module chaula.eduscore {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;

    opens chaula.eduscore to javafx.fxml;
    exports chaula.eduscore;

    opens chaula.eduscore.non_Academic;
    exports chaula.eduscore.non_Academic;

    opens chaula.eduscore.non_Academic.register_Student;
    exports chaula.eduscore.non_Academic.register_Student;

    opens chaula.eduscore.non_Academic.edit_Student;
    exports chaula.eduscore.non_Academic.edit_Student;

    opens chaula.eduscore.non_Academic.register_Teacher;
    exports chaula.eduscore.non_Academic.register_Teacher;

    opens chaula.eduscore.non_Academic.edit_Teacher;
    exports chaula.eduscore.non_Academic.edit_Teacher;
}