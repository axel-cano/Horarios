package org.orzhov.Gestores;

import org.orzhov.Controladores.CRUDController;

import java.sql.*;

public class SQLite {
    private Connection conexion;


    public SQLite() {
        try {
            String sqlite = "jdbc:sqlite:bd.sql";
            this.conexion = DriverManager.getConnection(sqlite);
            System.out.println("(SQLite) Se creo una base de datos temporal");
            //CRUDController.gestor("(SQLite) Se creo una base de datos temporal");
        } catch (Exception ex) {
            System.out.println("SQlite tuvo que ser creado en memoria");
            try {
                this.conexion = DriverManager.getConnection("jdbc:sqlite::memory:");
                //CRUDController.gestor("SQlite tuvo que ser creado en memoria");
            } catch (SQLException ex1) {
                System.out.println("SQlite no pudo ser creado en memoria");

            }
        }
    }


    public void crearTablas(Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("DROP TABLE IF EXISTS aulas;");
            statement.executeUpdate("DROP TABLE IF EXISTS carrera;");
            statement.executeUpdate("DROP TABLE IF EXISTS categorias_equipo;");
            statement.executeUpdate("DROP TABLE IF EXISTS equipo;");
            statement.executeUpdate("DROP TABLE IF EXISTS grupos;");
            statement.executeUpdate("DROP TABLE IF EXISTS materias;");
            statement.executeUpdate("DROP TABLE IF EXISTS plan_estudios;");
            statement.executeUpdate("DROP TABLE IF EXISTS usuarios;");


            statement.executeUpdate("CREATE TABLE aulas(\n" +
                    "    id_aula VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
                    "    nombre VARCHAR(100) NOT NULL,\n" +
                    "    tipo VARCHAR(20) NOT NULL,\n" +
                    "    capacidad INT(11) NOT NULL,\n" +
                    "    descripcion VARCHAR(100) NULL,\n" +
                    "    ubicacion VARCHAR(20) NULL\n" +
                    ");");

            statement.executeUpdate("CREATE TABLE carrera(\n" +
                    "    idcarrera TINYINT NOT NULL PRIMARY KEY,\n" +
                    "    nombre_carrera VARCHAR(100)\n" +
                    ");");

            statement.executeUpdate("CREATE TABLE categorias_equipo(\n" +
                    "    id_categoria INT(11) NOT NULL PRIMARY KEY,\n" +
                    "    nombre VARCHAR(100) NOT NULL,\n" +
                    "    descripcion VARCHAR(300)\n" +
                    ");");

            statement.executeUpdate("CREATE TABLE equipo(\n" +
                    "    id_equipo INT(11) NOT NULL PRIMARY KEY,\n" +
                    "    id_categoria INT(11) NOT NULL,\n" +
                    "    nombre VARCHAR(100) NOT NULL,\n" +
                    "    descripcion VARCHAR(100) NOT NULL,\n" +
                    "    CONSTRAINT FK_equipo_categoriaequipo_id_categoria FOREIGN KEY (id_categoria) REFERENCES categorias_equipo(id_categoria)\n" +
                    ");\n");

            statement.executeUpdate("CREATE TABLE grupos(\n" +
                    "    clv_grupo VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
                    "    turno BOOLEAN  \n" +
                    ");");

            statement.executeUpdate("CREATE TABLE plan_estudios(\n" +
                    "    clv_plan VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
                    "    nombre_plan VARCHAR(45) NOT NULL,\n" +
                    "    nivel VARCHAR(3) NOT NULL, -- falta el check\n" +
                    "    idcarrera TINYINT NOT NULL, \n" +
                    "    CONSTRAINT FK_planestudios_carrera_idcarrera FOREIGN KEY (idcarrera) REFERENCES carrera (idcarrera)      \n" +
                    "    );");

            statement.executeUpdate("CREATE TABLE materias\n(" +
                    "clv_materia VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
                    "nombre_materia VARCHAR(50) NOT NULL,\n" +
                    "creditos TINYINT NULL,\n" +
                    "cuatrimestre TINYINT NOT NULL,\n" +
                    "posicion TINYINT NOT NULL,\n" +
                    "clv_plan VARCHAR(10) NOT NULL,\n" +
                    "horas_x_semana TINYINT NOT NULL,\n" +
                    "tipo_materia CHAR(3) NOT NULL,\n" +
                    "CONSTRAINT FK_materias_planestudios_clv_plan FOREIGN KEY (clv_plan) REFERENCES plan_estudios(clv_plan)\n" +
                    ");");

            statement.executeUpdate("CREATE TABLE usuarios(\n" +
                    "    clv_usuario VARCHAR(50) NOT NULL PRIMARY KEY,\n" +
                    "    idcarrera TINYINT NOT NULL,\n" +
                    "    nombre_usuario VARCHAR(50),\n" +
                    "    nivel_ads VARCHAR(5) NOT NULL, -- falta el check\n" +
                    "    contrato VARCHAR(3) NOT NULL,   -- falta el check\n" +
                    "    CONSTRAINT FK_usuarios_carrera_idcarrera FOREIGN KEY (idcarrera) REFERENCES carrera(idcarrera)  ON DELETE NO ACTION ON UPDATE CASCADE\n" +
                    ");");
        } catch (Exception SQLException) {
            System.out.println("Inaccesible");
        }
    }


    public Connection getConexion() {
        return conexion;
    }

    public static void cargarDatos(Connection conexion, Connection sqlite) {
        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM aulas");

            while (rs.next()) {
                String sentencia2 = "INSERT INTO `aulas` (`id_aula`, `nombre`, `tipo`, `capacidad`, `descripcion`, `ubicacion`) VALUES ('" + rs.getString("id_aula") + "', '" + rs.getString("nombre") + "', '" + rs.getString("tipo") + "', " + Integer.parseInt(rs.getString("capacidad")) + ", '" + rs.getString("descripcion") + "', '" + rs.getString("ubicaciones") + "');";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }

        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM carrera");

            while (rs.next()) {
                String sentencia2 = "INSERT INTO `carrera` (`idcarrera`, `nombre_carrera`) VALUES ('" + Integer.parseInt(rs.getString("idcarrera")) + "');";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }

        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM categorias_equipo");

            while (rs.next()) {
                String sentencia2 = "INSERT INTO `categorias_equipo` (`id_categoria`, `nombre`, `descripcion`) VALUES (" + Integer.parseInt(rs.getString("id_categoria")) + ", '" + rs.getString("nombre") + "', '" + rs.getString("descripcion") + "');";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }

        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM categorias_equipo");

            while (rs.next()) {

                String sentencia2 = "INSERT INTO `equipo` (`id_equipo`, `id_categoria`, `nombre`, `descripcion`) VALUES (" + Integer.parseInt(rs.getString("id_equipo")) + ", " + Integer.parseInt(rs.getString("id_categoria")) + ", '" + rs.getString("nombre") + "', '" + rs.getString("descripcion") + "');";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }

        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM categorias_equipo");

            while (rs.next()) {
                String sentencia2 = "INSERT INTO `grupos` (`clv_grupo`, `turno`) VALUES ('" + rs.getString("clv_grupo") + "', " + rs.getString("turno") + ")";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }

        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM categorias_equipo");

            while (rs.next()) {
                String sentencia2 = "INSERT INTO `plan_estudios` (`clv_plan`, `nombre_plan`, `nivel`, `idcarrera`) VALUES ('" + rs.getString("clv_plan") + "', '" + rs.getString("nombre_plan") + "', '" + rs.getString("nivel") + "', " + Integer.parseInt(rs.getString("id_carrera")) + ");";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }

        try {
            Statement sentencia = sqlite.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM categorias_equipo");

            while (rs.next()) {
                String sentencia2 = "INSERT INTO `usuarios` (`clv_usuario`, `idcarrera`, `nombre_usuario`, `nivel_ads`, `contrato`) VALUES ('" + rs.getString("clv_usuario") + "', " + Integer.parseInt(rs.getString("idcarrera")) + ", '" + rs.getString("nombre_usuario") + "', '" + rs.getString("nivel_ads") + "', '" + rs.getString("contrato") + "');";
                PreparedStatement preparacion = conexion.prepareStatement(sentencia2);
                preparacion.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Algo paso al intentar cargar los datos");
        }
    }
}
