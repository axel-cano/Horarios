package org.orzhov.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.orzhov.Archivos.LecturaExcel;
import org.orzhov.Archivos.LecturaJSON;
import org.orzhov.Archivos.ObtenerRecursos;
import org.orzhov.CRUD.Lectura;
import org.orzhov.Excepciones.ConfigFormatoInvalidoException;
import org.orzhov.Main.App;
import org.orzhov.Main.Config;
import org.orzhov.Recursos.*;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

//mvn clean javafx:run

public class CRUDController implements Initializable {

    @FXML
    Label txtGestor, alerta,mensajeError;
    @FXML
        ComboBox idAulas,idAulasModificar,idModificarMaterias,idEliminarMaterias,idModificarPlan,idEliminarPlan,idModificarUsuarios,idEliminarUsuarios,idModificarCarrera,idEliminarCarrera,idModificarCategoria,idEliminarCategoria,idModificarEquipo,idEliminarEquipo,idModificarGrupos,idEliminarGrupos,turno,turnoss;
    @FXML
    TextField claveA,nombreA,tipoA,capacidadA,descripcionA,ubicacionA,claveM,nombreM,creditosM,cuatrimestreM,posicionM,clavePlanM,horasSemanaM,tipoMateria,claveP,nombreP,nivelP,IDP,claveU,nombreU,IDCarreraU,nivelU,contratoU,claveC,nombreC,claveCA,nombreCA,descripcionCA,IDE,IDCE,nombreE,descripcionE,claveG,nombreUsuario,password;
    @FXML
    TextField claveAA,nombreAA,tipoAA,capacidadAA,descripcionAA,ubicacionAA,claveMM,nombreMM,creditosMM,cuatrimestreMM,posicionMM,clavePlanMM,horasSemanaMM,tipoMateriaM,clavePP,nombrePP,nivelPP,IDPP,claveUU,nombreUU,IDCarreraUU,nivelUU,contratoUU,claveCC,nombreCC,claveCAA,nombreCAA,descripcionCAA,IDEE,IDCEE,nombreEE,descripcionEE,claveGG;
    @FXML
    Pane aulas,menu,agregarA,eliminarA,modificarA,materias,agregarM,eliminarM,modificarM,planEstudio,agregarP,eliminarP,modificarP,usuarios,agregarU,eliminarU,modificarU,carrera,agregarC,eliminarC,modificarC,categorias,agregarCA,eliminarCA,modificarCA,equipos,agregarE,eliminarE,modificarE,grupos,agregarG,eliminarG,modificarG,login;
    Connection conexion;

    @FXML
    TableView <Aula> tVA1;
    @FXML
    TableColumn <Aula, String> tCC1,tCE1;

    @FXML
    TableView <Materia> tVM1;
    @FXML
    TableColumn <Materia, String> tCIDM,tCNM,tCPM;

    @FXML
    TableView <PlanEstudio> tVP;
    @FXML
    TableColumn <PlanEstudio, String> tCIDP,tCCP;

    @FXML
    TableView <Usuario> tVU;
    @FXML
    TableColumn <Usuario, String> tCIDU,tCNU,tCCU;

    @FXML
    TableView <Carrera> tVC;
    @FXML
    TableColumn <Carrera, String> tCCC,tCNC;

    @FXML
    TableView <Categoria> tVCA;
    @FXML
    TableColumn <Categoria, String> tCCCA,tCNCA;

    @FXML
    TableView <Equipo> tVE;
    @FXML
    TableColumn <Equipo, String> tCIDE,tCNE;

    @FXML
    TableView <Grupo> tVG;
    @FXML
    TableColumn <Grupo, String> tCIDG;

    String sentencia, sql;
    PreparedStatement preparar;
    Statement statement;
    ResultSet resultado;
    Boolean horario;

    private ObservableList <String> observableAulas =  FXCollections.observableArrayList();
    private ObservableList <String> observableMaterias =  FXCollections.observableArrayList();
    private ObservableList <String> observablePlan =  FXCollections.observableArrayList();
    private ObservableList <String> observableUsuario =  FXCollections.observableArrayList();
    private ObservableList <Integer> observableCarrera =  FXCollections.observableArrayList();
    private ObservableList <Integer> observableCategoria =  FXCollections.observableArrayList();
    private ObservableList <Integer> observableEquipo =  FXCollections.observableArrayList();
    private ObservableList <String> observableGrupos =  FXCollections.observableArrayList();

    ObtenerRecursos recursos = new ObtenerRecursos(new LecturaJSON(App.getConf().getRuta()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        turno.setItems(FXCollections.observableArrayList("Matutino", "Vespertino"));
        turnoss.setItems(FXCollections.observableArrayList("Matutino", "Vespertino"));
        txtGestor.setText(App.getGestor());
        conexion = App.getConexion();
        try{
            sentencia = "SELECT id_aula, nombre, tipo, capacidad, descripcion, ubicacion FROM aulas";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getAulass().add(new Aula(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6)));
            }

            sentencia = "SELECT idcarrera, nombre_carrera FROM carrera";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getCarrerass().add(new Carrera(resultado.getInt(1), resultado.getString(2)));
            }

            sentencia = "SELECT id_categoria, nombre, descripcion FROM categorias_equipo";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getCategoriass().add(new Categoria(resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
            }

            sentencia = "SELECT id_equipo, id_categoria, nombre, descripcion FROM equipo";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getEquiposs().add(new Equipo(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getString(4)));
            }

            sentencia = "SELECT clv_grupo, turno FROM grupos";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getGruposs().add(new Grupo(resultado.getString(1), resultado.getBoolean(2)));
            }

            sentencia = "SELECT clv_plan, nombre_plan, nivel, idcarrera FROM plan_estudios";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getPlanEstudioss().add(new PlanEstudio(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getInt(4)));
            }

            sentencia = "SELECT clv_materia, nombre_materia, creditos, cuatrimestre, posicion, clv_plan, horas_x_semana, tipo_materia FROM materias";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getMateriass().add(new Materia(resultado.getString(1), resultado.getString(2), resultado.getInt(3), resultado.getInt(4), resultado.getInt(5), resultado.getString(6), resultado.getInt(7), resultado.getString(8)));
            }

            sentencia = "SELECT clv_usuario, idcarrera, nombre_usuario, nivel_ads, contrato FROM usuarios";
            preparar = App.getConexion().prepareStatement(sentencia);
            resultado = preparar.executeQuery();
            while(resultado.next()){
                recursos.getUsuarioss().add(new Usuario(resultado.getString(1), resultado.getInt(2), resultado.getString(3), resultado.getString(4), resultado.getString(5)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        cargarColumnasTablas();
        for (int i = 0; i < recursos.getAulass().size(); i++) {
            observableAulas.add(recursos.getAulass().get(i).getClave());
        }
        for (int i = 0; i < recursos.getMateriass().size(); i++) {
            observableMaterias.add(recursos.getMateriass().get(i).getClave());
        }
        for (int i = 0; i < recursos.getPlanEstudioss().size(); i++) {
            observablePlan.add(recursos.getPlanEstudioss().get(i).getClave());
        }
        for (int i = 0; i < recursos.getUsuarioss().size(); i++) {
            observableUsuario.add(recursos.getUsuarioss().get(i).getClave());
        }
        for (int i = 0; i < recursos.getCarrerass().size(); i++) {
            observableCarrera.add(recursos.getCarrerass().get(i).getClave());
        }
        for (int i = 0; i < recursos.getCategoriass().size(); i++) {
            observableCategoria.add(recursos.getCategoriass().get(i).getClave());
        }
        for (int i = 0; i < recursos.getEquiposs().size(); i++) {
            observableEquipo.add(recursos.getEquiposs().get(i).getIdEquipo());
        }
        for (int i = 0; i < recursos.getGruposs().size(); i++) {
            observableGrupos.add(recursos.getGruposs().get(i).getClave());
        }
    }

    public void login(){
        for (int i = 0; i < recursos.getUsuarioss().size(); i++) {
            if(nombreUsuario.getText().equals(recursos.getUsuarioss().get(i).getClave()) && password.getText().equals(recursos.getUsuarioss().get(i).getNombre())){
                login.setVisible(false);
                mensajeError.setVisible(false);
                menu.setVisible(true);
            }else{
                mensajeError.setVisible(true);
            }
        }
    }
    public void cerrarSecion(){
        menu.setVisible(false);
        mensajeError.setVisible(false);
        login.setVisible(true);
        nombreUsuario.setText("");
        password.setText("");
    }

    public void aulas(){
        aulas.setVisible(true);
        menu.setVisible(false);
    }
    public void salirAulas(){
        aulas.setVisible(false);
        agregarA.setVisible(false);
        eliminarA.setVisible(false);
        modificarA.setVisible(false);
        menu.setVisible(true);
    }
    public void materias(){
        materias.setVisible(true);
        menu.setVisible(false);
    }
    public void salirMaterias(){
        materias.setVisible(false);
        agregarM.setVisible(false);
        eliminarM.setVisible(false);
        modificarM.setVisible(false);
        menu.setVisible(true);
    }

    public void planEstudio(){
        planEstudio.setVisible(true);
        menu.setVisible(false);
    }
    public void salirPlan(){
        planEstudio.setVisible(false);
        agregarP.setVisible(false);
        eliminarP.setVisible(false);
        modificarP.setVisible(false);
        menu.setVisible(true);
    }

    public void usuarios(){
        usuarios.setVisible(true);
        menu.setVisible(false);
    }
    public void salirUsuarios(){
        usuarios.setVisible(false);
        agregarU.setVisible(false);
        eliminarU.setVisible(false);
        modificarU.setVisible(false);
        menu.setVisible(true);
    }

    public void carreras(){
        carrera.setVisible(true);
        menu.setVisible(false);
    }
    public void salirCarrera(){
        carrera.setVisible(false);
        agregarC.setVisible(false);
        eliminarC.setVisible(false);
        modificarC.setVisible(false);
        menu.setVisible(true);
    }

    public void categorias(){
        categorias.setVisible(true);
        menu.setVisible(false);
    }
    public void salirCategorias(){
        categorias.setVisible(false);
        agregarCA.setVisible(false);
        eliminarCA.setVisible(false);
        modificarCA.setVisible(false);
        menu.setVisible(true);
    }

    public void equipos(){
        equipos.setVisible(true);
        menu.setVisible(false);
    }
    public void salirEquipos(){
        equipos.setVisible(false);
        agregarE.setVisible(false);
        eliminarE.setVisible(false);
        modificarE.setVisible(false);
        menu.setVisible(true);
    }

    public void gruposs(){
        grupos.setVisible(true);
        menu.setVisible(false);
    }
    public void salirGrupos(){
        grupos.setVisible(false);
        agregarG.setVisible(false);
        eliminarG.setVisible(false);
        modificarG.setVisible(false);
        menu.setVisible(true);
    }

    public void salir() {
        System.exit(0);
    }

    public void agregarA(){
        agregarA.setVisible(true);
        eliminarA.setVisible(false);
        modificarA.setVisible(false);
    }

    public void modificarA(){
        agregarA.setVisible(false);
        eliminarA.setVisible(false);
        modificarA.setVisible(true);
    }

    public void eliminarA(){
        agregarA.setVisible(false);
        eliminarA.setVisible(true);
        modificarA.setVisible(false);
    }

    public void agregarM(){
        agregarM.setVisible(true);
        eliminarM.setVisible(false);
        modificarM.setVisible(false);
    }

    public void modificarM(){
        agregarM.setVisible(false);
        eliminarM.setVisible(false);
        modificarM.setVisible(true);
    }

    public void eliminarM(){
        agregarM.setVisible(false);
        eliminarM.setVisible(true);
        modificarM.setVisible(false);
    }

    public void agregarP(){
        agregarP.setVisible(true);
        eliminarP.setVisible(false);
        modificarP.setVisible(false);
    }

    public void modificarP(){
        agregarP.setVisible(false);
        eliminarP.setVisible(false);
        modificarP.setVisible(true);
    }

    public void eliminarP(){
        agregarP.setVisible(false);
        eliminarP.setVisible(true);
        modificarP.setVisible(false);
    }

    public void agregarU(){
        agregarU.setVisible(true);
        eliminarU.setVisible(false);
        modificarU.setVisible(false);
    }

    public void modificarU(){
        agregarU.setVisible(false);
        eliminarU.setVisible(false);
        modificarU.setVisible(true);
    }

    public void eliminarU(){
        agregarU.setVisible(false);
        eliminarU.setVisible(true);
        modificarU.setVisible(false);
    }

    public void agregarC(){
        agregarC.setVisible(true);
        eliminarC.setVisible(false);
        modificarC.setVisible(false);
    }

    public void modificarC(){
        agregarC.setVisible(false);
        eliminarC.setVisible(false);
        modificarC.setVisible(true);
    }

    public void eliminarC(){
        agregarC.setVisible(false);
        eliminarC.setVisible(true);
        modificarC.setVisible(false);
    }

    public void agregarCAA(){
        agregarCA.setVisible(true);
        eliminarCA.setVisible(false);
        modificarCA.setVisible(false);
    }

    public void modificarCAA(){
        agregarCA.setVisible(false);
        eliminarCA.setVisible(false);
        modificarCA.setVisible(true);
    }

    public void eliminarCAA(){
        agregarCA.setVisible(false);
        eliminarCA.setVisible(true);
        modificarCA.setVisible(false);
    }

    public void agregarE(){
        agregarE.setVisible(true);
        eliminarE.setVisible(false);
        modificarE.setVisible(false);
    }

    public void modificarE(){
        agregarE.setVisible(false);
        eliminarE.setVisible(false);
        modificarE.setVisible(true);
    }

    public void eliminarE(){
        agregarE.setVisible(false);
        eliminarE.setVisible(true);
        modificarE.setVisible(false);
    }

    public void agregarG(){
        agregarG.setVisible(true);
        eliminarG.setVisible(false);
        modificarG.setVisible(false);
    }

    public void modificarG(){
        agregarG.setVisible(false);
        eliminarG.setVisible(false);
        modificarG.setVisible(true);
    }

    public void eliminarG(){
        agregarG.setVisible(false);
        eliminarG.setVisible(true);
        modificarG.setVisible(false);
    }

    public void cargarColumnasTablas(){
        tCC1.setCellValueFactory(new PropertyValueFactory<Aula, String>("clave"));
        tCE1.setCellValueFactory(new PropertyValueFactory<Aula, String>("ubicacion"));
        tVA1.setItems(FXCollections.observableArrayList(recursos.getAulass()));
        idAulas.setItems(observableAulas);
        idAulasModificar.setItems(observableAulas);

        tCIDM.setCellValueFactory(new PropertyValueFactory<Materia, String>("clave"));
        tCNM.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombre"));
        tCPM.setCellValueFactory(new PropertyValueFactory<Materia, String>("clavePlan"));
        tVM1.setItems(FXCollections.observableArrayList(recursos.getMateriass()));
        idEliminarMaterias.setItems(observableMaterias);
        idModificarMaterias.setItems(observableMaterias);

        tCIDP.setCellValueFactory(new PropertyValueFactory<PlanEstudio, String>("clave"));
        tCCP.setCellValueFactory(new PropertyValueFactory<PlanEstudio, String>("nombre"));
        tVP.setItems(FXCollections.observableArrayList(recursos.getPlanEstudioss()));
        idEliminarPlan.setItems(observablePlan);
        idModificarPlan.setItems(observablePlan);

        tCIDU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("clave"));
        tCNU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tCCU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("idCarrera"));
        tVU.setItems(FXCollections.observableArrayList(recursos.getUsuarioss()));
        idEliminarUsuarios.setItems(observableUsuario);
        idModificarUsuarios.setItems(observableUsuario);

        tCCC.setCellValueFactory(new PropertyValueFactory<Carrera, String>("clave"));
        tCNC.setCellValueFactory(new PropertyValueFactory<Carrera, String>("nombre"));
        tVC.setItems(FXCollections.observableArrayList(recursos.getCarrerass()));
        idEliminarCarrera.setItems(observableCarrera);
        idModificarCarrera.setItems(observableCarrera);

        tCCCA.setCellValueFactory(new PropertyValueFactory<Categoria, String>("clave"));
        tCNCA.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombre"));
        tVCA.setItems(FXCollections.observableArrayList(recursos.getCategoriass()));
        idEliminarCategoria.setItems(observableCategoria);
        idModificarCategoria.setItems(observableCategoria);

        tCIDE.setCellValueFactory(new PropertyValueFactory<Equipo, String>("idEquipo"));
        tCNE.setCellValueFactory(new PropertyValueFactory<Equipo, String>("nombre"));
        tVE.setItems(FXCollections.observableArrayList(recursos.getEquiposs()));
        idEliminarEquipo.setItems(observableEquipo);
        idModificarEquipo.setItems(observableEquipo);

        tCIDG.setCellValueFactory(new PropertyValueFactory<Grupo, String>("clave"));
        tVG.setItems(FXCollections.observableArrayList(recursos.getGruposs()));
        idEliminarGrupos.setItems(observableGrupos);
        idModificarGrupos.setItems(observableGrupos);
    }

    public void agregarAula(){
        try{

            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `aulas` (`id_aula`, `nombre`, `tipo`,`capacidad`,`descripcion`, `ubicacion` ) VALUES ('"+claveA.getText().toString()+"','"+nombreA.getText().toString()+"','"+tipoA.getText().toString()+"','"+Integer.parseInt(capacidadA.getText().toString())+"','"+descripcionA.getText().toString()+"','"+ubicacionA.getText().toString()+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO aulas (id_aula,nombre,tipo,capacidad,descripcion,ubicacion)"
                            + "VALUES('"+claveA.getText().toString()+"','"+nombreA.getText().toString()+"','"+tipoA.getText().toString()+"',"+Integer.parseInt(capacidadA.getText().toString())+",'"+descripcionA.getText().toString()+"','"+ubicacionA.getText().toString()+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO aulas VALUES ('"+claveA.getText().toString()+"','"+nombreA.getText().toString()+"','"+tipoA.getText().toString()+"','"+Integer.parseInt(capacidadA.getText().toString())+"','"+descripcionA.getText().toString()+"','"+ubicacionA.getText().toString()+"')");
                    break;
            }
            recursos.getAulass().add(new Aula(claveA.getText().toString(),nombreA.getText().toString(),tipoA.getText().toString(),Integer.parseInt(capacidadA.getText().toString()),descripcionA.getText().toString(),ubicacionA.getText().toString()));
            observableAulas.add(claveA.getText().toString());
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarAula(){
        try {
            for (int i = 0; i < recursos.getAulass().size(); i++) {
                if(idAulasModificar.getValue().equals(recursos.getAulass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE aulas SET id_aula  = '" + claveAA.getText() + "', nombre ='"+ nombreAA.getText() +"', tipo='"+tipoAA.getText() + "', capacidad='"+Integer.parseInt(capacidadAA.getText()) +"', descripcion='"+descripcionAA.getText()+"',ubicacion='"+ubicacionAA.getText()+"' WHERE id_aula = '" + idAulasModificar.getValue().toString() + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE aulas SET id_aula = ? , "
                                    + "nombre = ? ,"
                                    + "tipo = ? ,"
                                    + "capacidad = ? ,"
                                    + "descripcion = ? ,"
                                    + "ubicacion = ? "
                                    + "WHERE id_aula = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,claveAA.getText());
                            preparar.setString(2,nombreAA.getText());
                            preparar.setString(3,tipoAA.getText());
                            preparar.setInt(4,Integer.parseInt(capacidadAA.getText()));
                            preparar.setString(5,descripcionAA.getText());
                            preparar.setString(6,idAulasModificar.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getAulass().remove(i);
                    observableAulas.remove(i);
                    recursos.getAulass().add(new Aula(claveAA.getText(),nombreAA.getText(),tipoAA.getText(),Integer.parseInt(capacidadAA.getText()),descripcionAA.getText(),ubicacionAA.getText()));
                    observableAulas.add(claveAA.getText());
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarAula(){
        try{
            for (int i = 0; i < recursos.getAulass().size(); i++) {
                if(idAulas.getValue().equals(recursos.getAulass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM aulas WHERE id_aula = '" + idAulas.getValue().toString() +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM aulas WHERE id_aula = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,idAulas.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getAulass().remove(i);
                    observableAulas.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarMaterias(){
        try{
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `materias` (`clv_materia`, `nombre_materia`, `creditos`,`cuatrimestre`,`posicion`, `clv_plan`,`horas_x_semana`,`tipo_materia` ) VALUES ('"+claveM.getText()+"','"+nombreM.getText()+"','"+Integer.parseInt(creditosM.getText())+"','"+Integer.parseInt(cuatrimestreM.getText())+"','"+Integer.parseInt(posicionM.getText())+"','"+clavePlanM.getText()+"','"+Integer.parseInt(horasSemanaM.getText())+"','"+tipoMateria.getText()+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO materias (clv_materia,nombre_materia,creditos,cuatrimestre,posicion,clv_plan,horas_x_semana,tipo_materia)"
                            + "VALUES('"+claveM.getText()+"','"+nombreM.getText()+"','"+Integer.parseInt(creditosM.getText())+"',"+Integer.parseInt(cuatrimestreM.getText())+",'"+Integer.parseInt(posicionM.getText())+",'"+clavePlanM.getText()+",'"+Integer.parseInt(horasSemanaM.getText())+"','"+tipoMateria.getText()+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO materias VALUES ('"+claveM.getText()+"','"+nombreM.getText()+"','"+Integer.parseInt(creditosM.getText())+"','"+Integer.parseInt(cuatrimestreM.getText())+"','"+Integer.parseInt(posicionM.getText())+"','"+clavePlanM.getText()+"','"+Integer.parseInt(horasSemanaM.getText())+"','"+tipoMateria.getText()+"')");
                    break;
            }
            recursos.getMateriass().add(new Materia(claveM.getText(),nombreM.getText(),Integer.parseInt(creditosM.getText()),Integer.parseInt(cuatrimestreM.getText()),Integer.parseInt(posicionM.getText()),clavePlanM.getText(),Integer.parseInt(horasSemanaM.getText()),tipoMateria.getText()));
            observableMaterias.add(claveM.getText());
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarMaterias(){
        try {
            for (int i = 0; i < recursos.getMateriass().size(); i++) {
                if(idModificarMaterias.getValue().equals(recursos.getMateriass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE materias SET clv_materia  = '" + claveMM.getText() + "', nombre_materia ='"+ nombreMM.getText() +"', creditos='"+Integer.parseInt(creditosMM.getText()) + "', cuatrimestre='"+Integer.parseInt(cuatrimestreMM.getText()) +"', posicion='"+Integer.parseInt(posicionMM.getText())+"', clv_plan='"+clavePlanMM.getText()+"', horas_x_semana='"+Integer.parseInt(horasSemanaMM.getText())+"',tipo_materia='"+tipoMateriaM.getText()+"' WHERE clv_materia = '" + idModificarMaterias.getValue().toString() + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE materias SET clv_materia = ? , "
                                    + "nombre_materia = ? ,"
                                    + "creditos = ? ,"
                                    + "cuatrimestre = ? ,"
                                    + "posicion = ? ,"
                                    + "clv_plan = ? ,"
                                    + "horas_x_semana = ? ,"
                                    + "tipo_materia = ? "
                                    + "WHERE clv_materia = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,claveMM.getText());
                            preparar.setString(2,nombreMM.getText());
                            preparar.setInt(3,Integer.parseInt(creditosMM.getText()));
                            preparar.setInt(4,Integer.parseInt(cuatrimestreMM.getText()));
                            preparar.setInt(5,Integer.parseInt(posicionMM.getText()));
                            preparar.setString(6,clavePlanMM.getText());
                            preparar.setInt(7,Integer.parseInt(horasSemanaMM.getText()));
                            preparar.setString(8,idModificarMaterias.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getMateriass().remove(i);
                    observableMaterias.remove(i);
                    recursos.getMateriass().add(new Materia(claveMM.getText(),nombreMM.getText(),Integer.parseInt(creditosMM.getText()),Integer.parseInt(cuatrimestreMM.getText()),Integer.parseInt(posicionMM.getText()),clavePlanMM.getText(),Integer.parseInt(horasSemanaMM.getText()),tipoMateriaM.getText()));
                    observableMaterias.add(claveMM.getText());
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarMaterias(){
        try{
            for (int i = 0; i < recursos.getMateriass().size(); i++) {
                if(idEliminarMaterias.getValue().equals(recursos.getMateriass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM materias WHERE clv_materia = '" + idEliminarMaterias.getValue().toString() +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM materias WHERE clv_materia = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,idEliminarMaterias.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getMateriass().remove(i);
                    observableMaterias.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarPlan(){
        try{
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `plan_estudios` (`clv_plan`, `nombre_plan`, `nivel`,`idcarrera` ) VALUES ('"+claveP.getText()+"','"+nombreP.getText()+"','"+nivelP.getText()+"','"+Integer.parseInt(IDP.getText())+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO plan_estudios (clv_plan,nombre_plan,nivel,idcarrera)"
                            + "VALUES('"+claveP.getText()+"','"+nombreP.getText()+"','"+nivelP.getText()+"','"+Integer.parseInt(IDP.getText())+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO plan_estudios VALUES ('"+claveP.getText()+"','"+nombreP.getText()+"','"+nivelP.getText()+"','"+Integer.parseInt(IDP.getText())+"')");
                    break;
            }
            recursos.getPlanEstudioss().add(new PlanEstudio(claveP.getText(),nombreP.getText(),nivelP.getText(),Integer.parseInt(IDP.getText())));
            observablePlan.add(claveP.getText());
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarPlan(){
        try {
            for (int i = 0; i < recursos.getPlanEstudioss().size(); i++) {
                if(idModificarPlan.getValue().equals(recursos.getPlanEstudioss().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE plan_estudios SET clv_plan  = '" + clavePP.getText() + "', nombre_plan ='"+ nombrePP.getText() +"', nivel='"+nivelPP.getText() + "',idcarrera='"+Integer.parseInt(IDPP.getText())+"' WHERE clv_plan = '" + idModificarPlan.getValue().toString() + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE plan_estudios SET clv_plan = ? , "
                                    + "nombre_plan = ? ,"
                                    + "nivel = ? ,"
                                    + "idcarrera = ? "
                                    + "WHERE clv_plan = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,clavePP.getText());
                            preparar.setString(2,nombrePP.getText());
                            preparar.setString(3,nivelPP.getText());
                            preparar.setInt(4,Integer.parseInt(IDPP.getText()));
                            preparar.setString(5,idModificarPlan.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getPlanEstudioss().remove(i);
                    observablePlan.remove(i);
                    recursos.getPlanEstudioss().add(new PlanEstudio(clavePP.getText(),nombrePP.getText(),nivelPP.getText(),Integer.parseInt(IDPP.getText())));
                    observablePlan.add(clavePP.getText());
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarPlan(){
        try{
            for (int i = 0; i < recursos.getPlanEstudioss().size(); i++) {
                if(idEliminarPlan.getValue().equals(recursos.getPlanEstudioss().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM plan_estudios WHERE clv_plan = '" + idEliminarPlan.getValue().toString() +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM plan_estudios WHERE clv_plan = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,idEliminarPlan.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getPlanEstudioss().remove(i);
                    observablePlan.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarUsuarios(){
        try{
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `usuarios` (`clv_usuario`, `idcarrera`, `nombre_usuario`,`nivel_ads`,`contrato`) VALUES ('"+claveU.getText()+"','"+Integer.parseInt(IDCarreraU.getText())+"','"+nombreU.getText()+"','"+nivelU.getText()+"','"+contratoU.getText()+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO usuarios (clv_usuario,idcarrera,nombre_usuario,nivel_ads,contrato)"
                            + "VALUES('"+claveU.getText()+"','"+Integer.parseInt(IDCarreraU.getText())+"','"+nombreU.getText()+"',"+nivelU.getText()+",'"+contratoU.getText()+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO usuarios VALUES ('"+claveU.getText()+"','"+Integer.parseInt(IDCarreraU.getText())+"','"+nombreU.getText()+"','"+nivelU.getText()+"','"+contratoU.getText()+"')");
                    break;
            }
            recursos.getUsuarioss().add(new Usuario(claveU.getText(),Integer.parseInt(IDCarreraU.getText()),nombreU.getText(),nivelU.getText(),contratoU.getText()));
            observableUsuario.add(claveU.getText());
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarUsuarios(){
        try {
            for (int i = 0; i < recursos.getUsuarioss().size(); i++) {
                if(idModificarUsuarios.getValue().equals(recursos.getUsuarioss().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE usuarios SET clv_usuario  = '" + claveUU.getText() + "', idcarrera ='"+ Integer.parseInt(IDCarreraUU.getText()) +"', nombre_usuario='"+nombreUU.getText() + "', nivel_ads='"+nivelUU.getText() +"',contrato='"+contratoUU.getText()+"' WHERE clv_usuario = '" + idModificarUsuarios.getValue().toString() + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE usuarios SET clv_usuario = ? , "
                                    + "idcarrera = ? ,"
                                    + "nombre_usuario = ? ,"
                                    + "nivel_ads = ? ,"
                                    + "contrato = ? "
                                    + "WHERE clv_usuario = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,claveUU.getText());
                            preparar.setInt(2,Integer.parseInt(IDCarreraUU.getText()));
                            preparar.setString(3,nombreUU.getText());
                            preparar.setString(4,nivelUU.getText());
                            preparar.setString(5,contratoUU.getText());
                            preparar.setString(6,idModificarUsuarios.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getUsuarioss().remove(i);
                    observableUsuario.remove(i);
                    recursos.getUsuarioss().add(new Usuario(claveUU.getText(),Integer.parseInt(IDCarreraUU.getText()),nombreUU.getText(),nivelUU.getText(),contratoUU.getText()));
                    observableUsuario.add(claveUU.getText());
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarUsuarios(){
        try{
            for (int i = 0; i < recursos.getUsuarioss().size(); i++) {
                if(idEliminarUsuarios.getValue().equals(recursos.getUsuarioss().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM usuarios WHERE clv_usuario = '" + idEliminarUsuarios.getValue().toString() +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM usuarios WHERE clv_usuario = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,idEliminarUsuarios.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getUsuarioss().remove(i);
                    observableUsuario.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarCarrera(){
        try{
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `carrera` (`idcarrera`, `nombre_carrera`) VALUES ('"+Integer.parseInt(claveC.getText())+"','"+nombreC.getText()+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO carrera (idcarrera,nombre_carrera)"
                            + "VALUES('"+Integer.parseInt(claveC.getText())+nombreC.getText()+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO carrera VALUES ('"+Integer.parseInt(claveC.getText())+"','"+nombreC.getText()+"')");
                    break;
            }
            recursos.getCarrerass().add(new Carrera(Integer.parseInt(claveC.getText()),nombreC.getText()));
            observableCarrera.add(Integer.parseInt(claveC.getText()));
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarCarrera(){
        try {
            for (int i = 0; i < recursos.getCarrerass().size(); i++) {
                if(idModificarCarrera.getValue().equals(recursos.getCarrerass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE carrera SET idcarrera  = '" + Integer.parseInt(claveCC.getText()) +"',nombre_carrera='"+nombreCC.getText()+"' WHERE idcarrera = '" + Integer.parseInt(idModificarCarrera.getValue().toString()) + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE carrera SET idcarrera = ? , "
                                    + "nombre_carrera = ? "
                                    + "WHERE idcarrera = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setInt(1,Integer.parseInt(claveCC.getText()));
                            preparar.setString(2,nombreCC.getText());
                            preparar.setInt(3,Integer.parseInt(idModificarCarrera.getValue().toString()));
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getCarrerass().remove(i);
                    observableCarrera.remove(i);
                    recursos.getCarrerass().add(new Carrera(Integer.parseInt(claveCC.getText()),nombreCC.getText()));
                    observableCarrera.add(Integer.parseInt(claveCC.getText()));
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarCarrera(){
        try{
            for (int i = 0; i < recursos.getCarrerass().size(); i++) {
                if(idEliminarCarrera.getValue().equals(recursos.getCarrerass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM carrera WHERE idcarrera = '" + Integer.parseInt(idEliminarCarrera.getValue().toString()) +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM carrera WHERE idcarrera = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setInt(1,Integer.parseInt(idEliminarCarrera.getValue().toString()));
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getCarrerass().remove(i);
                    observableCarrera.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarCategoria(){
        try{
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `categorias_equipo` (`id_categoria`, `nombre`, `descripcion` ) VALUES ('"+Integer.parseInt(claveCA.getText())+"','"+nombreCA.getText()+"','"+descripcionCA.getText()+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO categorias_equipo (id_categoria,nombre,descripcion)"
                            + "VALUES('"+Integer.parseInt(claveCA.getText())+"','"+nombreCA.getText()+"','"+descripcionCA.getText()+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO categorias_equipo VALUES ('"+Integer.parseInt(claveCA.getText())+"','"+nombreCA.getText()+"','"+descripcionCA.getText()+"')");
                    break;
            }
            recursos.getCategoriass().add(new Categoria(Integer.parseInt(claveCA.getText()),nombreCA.getText(),descripcionCA.getText()));
            observableCategoria.add(Integer.parseInt(claveCA.getText()));
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarCategoria(){
        try {
            for (int i = 0; i < recursos.getCategoriass().size(); i++) {
                if(idModificarCategoria.getValue().equals(recursos.getCategoriass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE categorias_equipo SET id_categoria  = '" + Integer.parseInt(claveCAA.getText()) + "', nombre ='"+ nombreCAA.getText() +"',descripcion='"+descripcionCAA.getText()+"' WHERE id_categoria = '" + Integer.parseInt(idModificarCategoria.getValue().toString()) + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE categorias_equipo SET id_categoria = ? , "
                                    + "nombre = ? ,"
                                    + "descripcion = ? "
                                    + "WHERE id_categoria = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setInt(1,Integer.parseInt(claveCAA.getText()));
                            preparar.setString(2,nombreCAA.getText());
                            preparar.setString(3,descripcionCAA.getText());
                            preparar.setInt(4,Integer.parseInt(idModificarCategoria.getValue().toString()));
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getCategoriass().remove(i);
                    observableCategoria.remove(i);
                    recursos.getCategoriass().add(new Categoria(Integer.parseInt(claveCAA.getText()),nombreCAA.getText(),descripcionCAA.getText()));
                    observableCategoria.add(Integer.parseInt(claveCAA.getText()));
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarCategoria(){
        try{
            for (int i = 0; i < recursos.getCategoriass().size(); i++) {
                if(idEliminarCategoria.getValue().equals(recursos.getCategoriass().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM categorias_equipo WHERE id_categoria = '" + Integer.parseInt(idEliminarCategoria.getValue().toString()) +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM categorias_equipo WHERE id_categoria = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setInt(1,Integer.parseInt(idEliminarCategoria.getValue().toString()));
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getCategoriass().remove(i);
                    observableCategoria.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarEquipo(){
        try{
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `equipo` (`id_equipo`, `id_categoria`, `nombre`,`descripcion` ) VALUES ('"+Integer.parseInt(IDE.getText())+"','"+Integer.parseInt(IDCE.getText())+"','"+nombreE.getText()+"','"+descripcionE.getText()+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO equipo (id_equipo,id_categoria,nombre,descripcion)"
                            + "VALUES('"+Integer.parseInt(IDE.getText())+"',"+Integer.parseInt(IDCE.getText())+",'"+nombreE.getText()+"','"+descripcionE.getText()+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO equipo VALUES ('"+Integer.parseInt(IDE.getText())+"','"+Integer.parseInt(IDCE.getText())+"','"+nombreE.getText()+"','"+descripcionE.getText()+"')");
                    break;
            }
            recursos.getEquiposs().add(new Equipo(Integer.parseInt(IDE.getText()),Integer.parseInt(IDCE.getText()),nombreE.getText(),descripcionE.getText()));
            observableEquipo.add(Integer.parseInt(IDE.getText()));
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarEquipo(){
        try {
            for (int i = 0; i < recursos.getEquiposs().size(); i++) {
                if(idModificarEquipo.getValue().equals(recursos.getEquiposs().get(i).getIdEquipo())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE equipo SET id_equipo  = '" + Integer.parseInt(IDEE.getText()) + "', id_categoria ='"+ Integer.parseInt(IDCEE.getText()) +"', nombre='"+nombreEE.getText() + "',descripcion='"+descripcionEE.getText()+"' WHERE id_equipo = '" + Integer.parseInt(idModificarEquipo.getValue().toString()) + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE equipo SET id_equipo = ? , "
                                    + "id_categoria = ? ,"
                                    + "nombre = ? ,"
                                    + "descripcion = ? "
                                    + "WHERE id_equipo = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setInt(1,Integer.parseInt(IDEE.getText()));
                            preparar.setInt(2,Integer.parseInt(IDCEE.getText()));
                            preparar.setString(3,nombreEE.getText());
                            preparar.setString(4,descripcionEE.getText());
                            preparar.setInt(5,Integer.parseInt(idModificarEquipo.getValue().toString()));
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getEquiposs().remove(i);
                    observableEquipo.remove(i);
                    recursos.getEquiposs().add(new Equipo(Integer.parseInt(IDEE.getText()),Integer.parseInt(IDCEE.getText()),nombreEE.getText(),descripcionEE.getText()));
                    observableEquipo.add(Integer.parseInt(IDEE.getText()));
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarEquipo(){
        try{
            for (int i = 0; i < recursos.getEquiposs().size(); i++) {
                if(idEliminarEquipo.getValue().equals(recursos.getEquiposs().get(i).getIdEquipo())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM equipo WHERE id_equipo = '" + Integer.parseInt(idEliminarEquipo.getValue().toString()) +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM equipo WHERE id_equipo = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setInt(1,Integer.parseInt(idEliminarEquipo.getValue().toString()));
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getEquiposs().remove(i);
                    observableEquipo.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void agregarGrupo(){
        try{
            if (turno.getSelectionModel().getSelectedItem() != null && turno.getSelectionModel().getSelectedItem().equals("Matutino")) {
                horario = false;
            }else if(turno.getSelectionModel().getSelectedItem() != null){
                horario = true;
            }
            switch (App.getBaseDeDatos()){
                case 1:
                    sentencia = "INSERT INTO `grupos` (`clv_grupo`, `turno` ) VALUES ('"+claveG.getText()+"','"+horario+ "')";
                    preparar = App.getConexion().prepareStatement(sentencia);
                    preparar.executeUpdate();
                    break;
                case 2:
                    statement = App.getConexion().createStatement();
                    sql = "INSERT INTO grupos (clv_grupo,turno)"
                            + "VALUES('"+claveG.getText()+"','"+horario+ "');";
                    statement.executeUpdate(sql);
                    break;
                case 3:
                    statement = App.getConexion().createStatement();
                    statement.executeUpdate("INSERT INTO grupos VALUES ('"+claveG.getText()+"','"+horario+"')");
                    break;
            }
            recursos.getGruposs().add(new Grupo(claveG.getText(),horario));
            observableGrupos.add(claveG.getText());
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void actualizarGrupo(){
        try {
            for (int i = 0; i < recursos.getGruposs().size(); i++) {
                if(idModificarGrupos.getValue().equals(recursos.getGruposs().get(i).getClave())){
                    if (turnoss.getSelectionModel().getSelectedItem() != null && turnoss.getSelectionModel().getSelectedItem().equals("Matutino")) {
                        horario = false;
                    }else if(turnoss.getSelectionModel().getSelectedItem() != null){
                        horario = true;
                    }
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "UPDATE grupos SET clv_grupo  = '" + claveGG.getText() + "',turno='"+horario+"' WHERE clv_grupo = '" + idModificarGrupos.getValue().toString() + "'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "UPDATE grupos SET clv_grupo = ? , "
                                    + "turno = ? "
                                    + "WHERE clv_grupo = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,claveGG.getText());
                            preparar.setBoolean(2,horario);
                            preparar.setString(3,idModificarGrupos.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getGruposs().remove(i);
                    observableGrupos.remove(i);
                    recursos.getGruposs().add(new Grupo(claveGG.getText(),horario));
                    observableGrupos.add(claveGG.getText());
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void eliminarGrupo(){
        try{
            for (int i = 0; i < recursos.getGruposs().size(); i++) {
                if(idEliminarGrupos.getValue().equals(recursos.getGruposs().get(i).getClave())){
                    switch (App.getBaseDeDatos()){
                        case 1:
                            sentencia = "DELETE FROM grupos WHERE clv_grupo = '" + idEliminarGrupos.getValue().toString() +"'";
                            preparar = App.getConexion().prepareStatement(sentencia);
                            preparar.executeUpdate();
                            break;
                        default:
                            sql = "DELETE FROM grupos WHERE clv_grupo = ?";
                            preparar = App.getConexion().prepareStatement(sql);
                            preparar.setString(1,idEliminarGrupos.getValue().toString());
                            preparar.executeUpdate();
                            break;
                    }
                    recursos.getGruposs().remove(i);
                    observableGrupos.remove(i);
                    break;
                }
            }
            cargarColumnasTablas();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
