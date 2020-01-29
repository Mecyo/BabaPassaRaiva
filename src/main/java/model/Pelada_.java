package model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-01-29T03:28:49.507-0200")
@StaticMetamodel(Pelada.class)
public class Pelada_ {
  public static volatile SingularAttribute<Pelada, Integer> id;
  public static volatile SingularAttribute<Pelada, String> nomeEvento;
  public static volatile SingularAttribute<Pelada, Calendar> data;
  public static volatile SingularAttribute<Pelada, String> local;
  public static volatile ListAttribute<Pelada, Usuario> jogadores;
  public static volatile ListAttribute<Pelada, Usuario> convidados;
  public static volatile SingularAttribute<Pelada, Usuario> owner;
}