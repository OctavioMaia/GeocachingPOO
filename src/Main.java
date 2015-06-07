package src;
import java.util.*;
import java.io.*;
import static java.lang.System.out;
import Exceptions.*;

/**
 * Classe main, chama os menus e as funcoes 
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class Main implements Serializable{
   private Core core = new Core();
   private Persistencia state = new Persistencia();
   int decisao;
   private Scanner inputMain = new Scanner(System.in).useDelimiter("\\n");
   
   /**
    * A função run é responsável por executar o primeiro menu (login/registo).
    */
   public void run(){  
        boolean flag = true;
        
        while(flag){
            out.println("-----------GeocachingPOO----------");
            out.println("1 - Registar utilizador");
            out.println("2 - Login");
            out.println("3 - Exit");
            out.println("----------------------------------");
            out.print("Decisão: " );
         
            try{
                decisao = inputMain.nextInt();
                
                switch(decisao){
                    case 1:
                        core.registarUser();
                        break; 
                        
                    case 2:
                        if(core.login()==1){
                            afterLogin();
                        }
                        break;
                        
                    case 3:
                        flag=false;
                        break;
                        
                    default:
                        throw new SelecaoInvalidaException();                    
                    }
                }catch(InputMismatchException e){
                    out.println("Verifique se introduziu um número!");
                    flag=false;
                }catch(SelecaoInvalidaException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(EmailInvalidoException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(NomeInvalidoException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(SexoInvalidoException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(EmailJaExisteException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(DataInvalidaException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(NumberFormatException e){
                    out.println(e.getMessage());
                    flag=false;
                    run();
                }catch(Exception e){
                    out.println("Exception geral?");
                    flag=false;
                    run();
                }
            out.print("\n");
        }   
   }
    
   /**
    * A função afterLogin é responsável por executar o segundo menu, após login com sucesso.
    */
   public void afterLogin(){ 
        boolean flag = true;
        
        while(flag){
            if(core.isAdmin()) out.println("\n-----------GeocachingPOO: Área de membros (admin)---------");
            else out.println("\n-----------GeocachingPOO: Área de membros---------");
            out.println("-----------------Bem vindo " + core.getCurrentUser().getNome() + "----------------");
            out.println("1 - Verificar caches existentes");
            out.println("2 - Verificar caches reportadas");
            out.println("3 - Inserir atividade de descoberta de cache");
            out.println("4 - Remover atividade de descoberta de cache");
            out.println("5 - Registar cache");
            out.println("6 - Remover cache");
            out.println("7 - Report abuse cache");
            out.println("8 - Ver lista de amigos");
            out.println("9 - Ver pedidos de amizade");
            out.println("10 - Enviar pedido de amizade");
            out.println("11 - Aceitar pedido de amizade");
            out.println("12 - Rejeitar pedido de amizade");
            out.println("13 - Remover amigo");
            out.println("14 - Consultar atividades");
            out.println("15 - Consultar estatisticas");
            out.println("16 - Verificar eventos existentes");
            out.println("17 - Participar num evento");
            out.println("18 - Adicionar evento");
            out.println("19 - Remover evento");
            out.println("20 - Simular evento");
            out.println("21 - Definições de conta");
            out.println("22 - Lista utilizadores");
            out.println("23 - Remover conta");
            out.println("24 - Carregar estado");
            out.println("25 - Guardar estado");
            out.println("26 - Exit");
            out.println("----------------------------------");
            out.print("Decisão: " );
            
            try{ 
                decisao = inputMain.nextInt();
                out.print("\n");   
                switch(decisao){
                    case 1:
                        core.cachesExistentes();
                        break;
                        
                    case 2:
                        core.cachesReportadas();
                        break;
                                            
                    case 3:
                        core.inserirAtividade();
                        break;
                        
                    case 4:
                        core.removeAtividade();
                        break;
                        
                    case 5:
                        core.registarCache();
                        break;
                        
                    case 6:
                        core.removerCache();
                        break;
                        
                    case 7:
                        core.reportarCache();
                        break;
                        
                    case 8:
                        core.listaAmigos();
                        break;
                        
                    case 9:
                        core.pedidosAmizade();
                        break;
                    
                    case 10:
                        core.enviarPedidoAmizade();
                        break;
                        
                    case 11:
                        core.aceitarPedidoAmizade();
                        break;
                        
                    case 12:
                        core.rejeitarPedidoAmizade();
                        break;
                        
                    case 13:
                        core.removerAmigo();
                        break;
                        
                    case 14:
                        core.consultaAtividades();
                        break;
                        
                    case 15:
                        core.estatisticasUser();
                        break;
                         
                    case 16:
                        core.listaEventos();
                        break;
                    
                    case 17:
                        core.participarEvento();
                        break;
                    
                    case 18:
                        core.adicionarEvento();
                        break;
                    
                    case 19:
                        core.removerEvento();
                        break;
                        
                    case 20:
                        core.simularEvento();
                        break;
                        
                    case 21:
                        core.definicoesConta();
                        break;
                        
                    case 22:
                        core.listaUsers();
                        break;
                        
                    case 23:
                        if(core.removeUser()) flag=false;
                        break;
                                                
                    case 24:
                        if(core.isAdmin()){
                            core = state.carregarEstado();
                            out.println("Estado carregado com sucesso, por favor faça login de novo!\n");
                            flag=false;
                        }else 
                            throw new PrivilegiosInsuficientesException();
                        
                        break;
                        
                    case 25:
                        if(core.isAdmin()){
                            state.guardarEstado(core);
                            out.println("Estado guardado com sucesso!\n");
                        }else 
                            throw new PrivilegiosInsuficientesException();
                            
                        break;
                    
                    case 26:
                        flag=false;
                        break;
                        
                    default:
                        throw new SelecaoInvalidaException();
                        
                }        
            }catch(InputMismatchException e){
                out.println("Verifique se introduziu um número!");
                flag=false;
            }catch(SelecaoInvalidaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(CacheNaoExisteException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(AtividadeJaDescobertaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(DataInvalidaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(SolucaoErradaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(TesouroInvalidoException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(AtividadeNullException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(DificuldadeInvalidaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(PrivilegiosInsuficientesException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(AmigoJaAdicionadoException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(UtilizadorNaoExisteException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(AdicionarMesmaPessoaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(PedidoNaoExisteException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(EventoNaoExisteException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(JaInscritoEventoException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(InscricoesCheiasException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(PassouDataLimiteException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(CacheJaInseridaException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(NaoExistemParticipantesException e){
                out.println(e.getMessage());
                flag=false;
                afterLogin();
            }catch(Exception e){
                out.println("Exception geral?");
                flag=false;
                afterLogin();
            }
        }
    }
}