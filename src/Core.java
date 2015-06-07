package src;

import java.util.*;
import static java.lang.System.out;
import Exceptions.*;
import java.io.*;

/**
 * Class Core, contem todas as funcoes necessarias para o projeto
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class Core implements Serializable {
    private TreeMap<String, Utilizador> utilizadores = new TreeMap<String, Utilizador>();
    private TreeMap<String, Cache> caches = new TreeMap<String, Cache>();
    private TreeMap<String, Cache> reportedCaches = new TreeMap<String, Cache>();
    private TreeMap<String, Evento> eventos = new TreeMap<String, Evento>();
    static Scanner input = new Scanner(System.in).useDelimiter("\\n");

    Utilizador admin = new Utilizador("admin", "123", "Administrador", 'M',"Portugal", new GregorianCalendar(2000, 1, 1), 1);
    Utilizador currentUser = new Utilizador();
    Cache cache1 = new Cache("Octávio Maia", new Coordenadas(1, 1), 2,"Procurem pela arvore mais alta", null);

    /**
     * Construtor vazio.
     */
    public Core() {
        // inserir admin
        utilizadores.put(admin.getEmail(), admin);
    }

    /**
     * A função getCurrentUser devolve um Utilizador.
     */
    public Utilizador getCurrentUser() {
        return currentUser.clone();
    }

    // -----BEFORE LOGIN--------
    /**
     * A função registarUser regista um Utilizador.
     */
    public void registarUser() throws EmailInvalidoException,EmailJaExisteException, NomeInvalidoException,SexoInvalidoException, DataInvalidaException {
        for (int i = 0; i < 100; i++) out.println();

        Utilizador user = new Utilizador();
        String email, pw, nome, morada, data;
        String[] dataSplit;
        char sexo;
        int dia, mes, ano;

        out.println("------Registo de utilizador------");
        out.print("Introduza email:  ");
        email = input.next();

        if (!email.contains("@") || !email.contains("."))
            throw new EmailInvalidoException(); // um email tem de ter um @ e um .

        if (utilizadores.containsKey(email))
            throw new EmailJaExisteException();

        out.print("Introduza password:  ");
        pw = input.next();

        out.print("Introduza nome:  ");
        nome = input.next();

        if (nome.matches("[0-9]+"))
            throw new NomeInvalidoException(); // o nome de uma pessoa nao pode ter numeros

        out.print("Introduza morada:  ");
        morada = input.next();

        out.print("Introduza sexo: (M/F) ");
        sexo = input.next().charAt(0);

        if (sexo != 'F' && sexo != 'M')
            throw new SexoInvalidoException();

        out.print("Introduza data de nascimento: (Dia/Mes/Ano) ");
        data = input.next();
        dataSplit = data.split("/");

        try{
            dia = Integer.parseInt(dataSplit[0]);
            mes = Integer.parseInt(dataSplit[1]);
            ano = Integer.parseInt(dataSplit[2]);
        }catch (Exception e) { //nao sei de outra maneira de o fazer
            throw new DataInvalidaException();
        }

        if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && String.valueOf(ano).length() == 4) {
            GregorianCalendar nascimento = new GregorianCalendar(ano, mes, dia);
            user = new Utilizador(email, pw, nome, sexo, morada, nascimento, 0);
        }else
            throw new DataInvalidaException();

        utilizadores.put(user.getEmail(), user);
        out.println("Registado com sucesso!\n");
    }

    // case 2 before login
    /**
     * A função login faz o login de um Utilizador.
     */
    public int login() {
        for (int i = 0; i < 100; i++) out.println();
        int valor = 0;
        String email, pw;
        Utilizador user = new Utilizador();

        out.println("------Login------");
        out.print("Introduza email:  ");
        email = input.next();
        out.print("Introduza password:  ");
        pw = input.next();

        if (utilizadores.containsKey(email) && utilizadores.get(email).getPw().equals(pw)) {
            for (int i = 0; i < 100; i++) out.println();
            out.println("Login com sucesso!");
            currentUser = utilizadores.get(email);
            valor = 1;
        } else {
            for (int i = 0; i < 100; i++) out.println();
            out.println("Login sem sucesso!");
            valor = 0;
        }
        return valor;
    }

    // ------------------AFTER LOGIN----------------
    /**
     * A função cachesExistentes verifica quais são as caches existentes.
     */
    public void cachesExistentes() {
        for (int i = 0; i < 100; i++) out.println();
        out.println("-----------GeocachingPOO: Caches existentes---------");

        for (Map.Entry<String, Cache> entry : caches.entrySet()){
            out.println(entry.getValue().toString(entry.getValue()));
            out.println("----------------------------------------------------");
        }
        out.println("Existem " + caches.size() + " caches no sistema GeocachingPOO");
        out.println("----------------------------------------------------");
    }

    /**
     * A função cachesReportadas verifica se existem anomalias nas caches.
     */
    public void cachesReportadas() {
        for (int i = 0; i < 100; i++) out.println();
        out.println("-----------GeocachingPOO: Caches reportadas---------");

        for(Map.Entry<String, Cache> entry : reportedCaches.entrySet()) {
            out.println("Criador: " + entry.getValue().getCriador());
            out.println("Tipo: " + entry.getValue().getTipo());
            out.println("Código identificador: " + entry.getValue().getCodigo());
            out.println("Motivo reporte: " + entry.getValue().getReportMotive());
            out.println("----------------------------------------------------\n");
        }
        out.println("Existem " + reportedCaches.size() + " caches reportadas no sistema GeocachingPOO");
        out.println("----------------------------------------------------");
    }

    /**
     * A função inserirAtividade insere uma atividade.
     */
    public void inserirAtividade() throws CacheNaoExisteException, AtividadeJaDescobertaException, DataInvalidaException, SelecaoInvalidaException, SolucaoErradaException, TesouroInvalidoException {
        for (int i = 0; i < 100; i++) out.println();
        String codigoIntroduzido;
        char confirmacao;
        TreeMap<GregorianCalendar, Cache> ativUser = currentUser.getAtividades();

        out.println("-----------GeocachingPOO: Inserir atividade de descoberta de cache---------");
        out.print("Introduza o código da cache que ira procurar: ");
        codigoIntroduzido = input.next();

        String tipo = caches.get(codigoIntroduzido).getTipo();

        Cache aux = caches.get(codigoIntroduzido);

        if (aux == null)
            throw new CacheNaoExisteException();

        out.println("Encontramos a seguinte cache:");
        out.println("\tCriador: " + aux.getCriador());
        out.println("\tTipo: " + aux.getTipo());
        if (!aux.getTipo().equals("Cache Misterio")) //temos isto pois a cache misterio requer resolver um enigma para saber as coordenadas e nao queremos dizer logo ao user as coordenadas!
            out.println("\tCoordenadas: " + aux.getCoordenadas().toString(aux.getCoordenadas()));
        out.println("\tDificuldade: " + aux.getDificuldade());
        out.println("\tDescrição: " + aux.getDescricao());
        out.println("---------------------------------------------");
        out.print("É esta a cache pretendida? (y/n) : ");
        confirmacao = input.next().charAt(0);

        if (confirmacao == 'y') {
            String data;
            String[] dataSplit;
            int dia, mes, ano, hora, minuto;

            for (Map.Entry<GregorianCalendar, Cache> entry : ativUser.entrySet()) {
                Cache c = entry.getValue();
                if (c.equals(caches.get(codigoIntroduzido))) {
                    throw new AtividadeJaDescobertaException();
                }
            }

            if (aux.getTipo().equals("Cache Misterio")) { // a cache misterio e necessario resolver os enigmas!
                CacheMisterio caux = (CacheMisterio) aux;
                out.println("Enigma da latitude: " + caux.getEnigmaLatitude());
                out.print("\tSolução do enigma: ");
                String sol1 = input.next();
                if (caux.getPuzzle().containsKey(sol1)){
                    out.println("\tParabéns descobriu a coordenada da latitude: "+caux.getPuzzle().get(sol1));
                }else{
                    throw new SolucaoErradaException();
                }

                out.println("Enigma da longitude: " + caux.getEnigmaLongitude());
                out.print("\tSolução do enigma: ");
                String sol2 = input.next();
                if (caux.getPuzzle().containsKey(sol2)){
                    out.println("\tParabéns descobriu a coordenada da longitude: "+caux.getPuzzle().get(sol2));
                }else{
                    throw new SolucaoErradaException();
                }
            }

            out.print("Introduza data em que encontrou a cache: (Dia/Mes/Ano) ");
            data = input.next();
            dataSplit = data.split("/");
            try{
                dia = Integer.parseInt(dataSplit[0]);
                mes = Integer.parseInt(dataSplit[1]);
                ano = Integer.parseInt(dataSplit[2]);
            }catch(Exception e){
                throw new DataInvalidaException();
            }

            out.print("Introduza a hora em que encontrou a cache: ");
            hora = input.nextInt();
            out.print("Introduza o minuto em que encontrou a cache: ");
            minuto = input.nextInt();

            if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && String.valueOf(ano).length() == 4 && hora >= 0 && hora <= 23 && minuto >= 0 && minuto <= 59) {
                GregorianCalendar d = new GregorianCalendar(ano, mes, dia,hora, minuto);
                currentUser.adicionarAtividade(d,aux);
                currentUser.addPontuacao(aux.getPontuacao() + (aux.getDificuldade() / 2));
                aux.addVisita(currentUser.getNome());
            }else{
                throw new DataInvalidaException();
            }
            // Handle tesouros
            if (aux.getTipo().equals("Cache Normal") || aux.getTipo().equals("Cache Misterio")) {
                out.print("\nDeseja adicionar ou remover tesouros? (y/n) : ");
                if (input.next().charAt(0) == 'y') {
                    out.println("Esta cache possui os seguintes tesouros:");
                    for (String entry : aux.getTesouros()) {
                        out.println("\t-> " + entry);
                    }

                    out.print("Introduza o nome do tesouro que pretende adicionar: ");
                    String tesouro = input.next();

                    if (tesouro.equals(""))
                        throw new TesouroInvalidoException();
                    else{
                        if(!aux.getTesouros().contains(tesouro)) {
                            aux.addTesouro(tesouro);
                            out.println(tesouro + " adicionado com sucesso!");
                        }else
                            out.println("Já existe um tesouro com esse nome, logo não foi adicionado!");
                    }
                    out.print("Introduza o nome do tesouro que pretende retirar: ");
                    String removeT = input.next();
                    if (!removeT.equals("") && aux.getTesouros().contains(removeT)) {
                        aux.removeTesouro(removeT);
                        out.println(removeT + " retirado com sucesso!");
                    }else
                        out.println("Não existe nenhum tesouro com esse nome, logo não retiramos nada!");
                }
            }
            out.println("Parabéns encontrou a sua cache nº" + (currentUser.getAtividades().size()) + "!");
        }
    }

    /**
     * A função removeAtividade remove uma atividade.
     */
    public void removeAtividade() throws CacheNaoExisteException,AtividadeNullException, CacheNaoExisteException {
        for (int i = 0; i < 100; i++) out.println();
        String codigoIn;
        char confirm;

        out.println("-----------GeocachingPOO: Remover atividade de descoberta de cache---------");
        out.print("Introduza o código da cache que irá remover das suas atividades: ");
        codigoIn = input.next();

        if (currentUser.removerAtividade(codigoIn))
            out.println("Removido com sucesso");
        else
            throw new CacheNaoExisteException();
    }

    /**
     * A função registarCache regista uma cache.
     */
    public void registarCache() throws SelecaoInvalidaException,DificuldadeInvalidaException {
        for (int i = 0; i < 100; i++)out.println();
        int tipo,dificuldade, nrInter;;
        String criador, descricao;
        Coordenadas coord = new Coordenadas();
        Boolean f = true;
        criador = currentUser.getNome();

        out.println("------Inserção de cache------");
        out.println("1 - Cache Normal");
        out.println("2 - Micro Cache");
        out.println("3 - Multi Cache");
        out.println("4 - Cache Misterio");
        out.println("5 - Cache Evento");
        out.println("6 - Cache Virtual");
        out.println("7 - Exit");
        out.println("-----------------------------");

        while (f) {
            out.print("Tipo de cache que pretende inserir (1-6) : ");
            tipo = input.nextInt();
            switch (tipo) {
                case 1:
                Cache cache1 = new Cache();

                out.println("Criador: " + criador);
                cache1.setCriador(criador);

                out.println("Introduza as coordenadas (se for decimal separar por virgula):");
                out.print("\tLatitude: ");
                coord.setLatitude(input.nextDouble());
                out.print("\tLongitude: ");
                coord.setLongitude(input.nextDouble());
                cache1.setCoordenadas(coord);

                out.print("Introduza descrição: ");
                descricao = input.next();
                cache1.setDescricao(descricao);

                out.print("Introduza dificuldade (1-5): ");
                dificuldade = input.nextInt();

                if (dificuldade >= 1 && dificuldade <= 5)
                    cache1.setDificuldade(dificuldade);
                else
                    throw new DificuldadeInvalidaException();

                caches.put(cache1.getCodigo(), cache1);
                out.println(cache1.getTipo() + " adicionada com sucesso!\n");
                f = false;
                break;

                case 2:
                CacheMicro cache2 = new CacheMicro();

                out.println("Criador: " + criador);
                cache2.setCriador(criador);

                out.println("Introduza as coordenadas (se for decimal separar por virgula):");
                out.print("\tLatitude: ");
                coord.setLatitude(input.nextDouble());
                out.print("\tLongitude: ");
                coord.setLongitude(input.nextDouble());
                cache2.setCoordenadas(coord);

                out.print("Introduza descrição: ");
                descricao = input.next();
                cache2.setDescricao(descricao);

                out.print("Introduza dificuldade (1-5): ");
                dificuldade = input.nextInt();

                if (dificuldade >= 1 && dificuldade <= 5)
                    cache2.setDificuldade(dificuldade);
                else
                    throw new DificuldadeInvalidaException();

                caches.put(cache2.getCodigo(), cache2);
                out.println(cache2.getTipo() + " adicionada com sucesso!\n");
                f = false;
                break;

                case 3:
                CacheMulti cache3 = new CacheMulti();

                out.println("Criador: " + criador);
                cache3.setCriador(criador);

                out.print("Quantas caches intermedias irão existir? : ");
                nrInter = input.nextInt();

                for (int i = 1; i <= nrInter; i++) {
                    Coordenadas coordIntermedias = new Coordenadas();
                    out.println("------Cache Intermedia nº" + i + "--------");
                    out.println("Introduza as coordenadas da cache intermedia (se for decimal separar por virgula):");
                    out.print("\tLatitude: ");
                    coordIntermedias.setLatitude(input.nextDouble());
                    out.print("\tLongitude: ");
                    coordIntermedias.setLongitude(input.nextDouble());

                    cache3.adicionarCodigosIntermedios(i, UUID.randomUUID().toString().replace("-", "").substring(0, 6));
                    cache3.adicionarCachesIntermedias(i, coordIntermedias);
                }

                out.println("------Cache Final--------");
                out.println("Introduza as coordenadas da cache final (se for decimal separar por virgula):");
                out.print("\tLatitude: ");
                coord.setLatitude(input.nextDouble());
                out.print("\tLongitude: ");
                coord.setLongitude(input.nextDouble());
                cache3.setCoordenadas(coord);

                out.print("Introduza descrição: ");
                descricao = input.next();
                cache3.setDescricao(descricao);

                out.print("Introduza dificuldade (1-5): ");
                dificuldade = input.nextInt();

                if (dificuldade >= 1 && dificuldade <= 5)
                    cache3.setDificuldade(dificuldade);
                else
                    throw new DificuldadeInvalidaException();

                caches.put(cache3.getCodigo(), cache3);
                out.println(cache3.getTipo() + " adicionada com sucesso!\n");
                f = false;
                break;

                case 4:
                CacheMisterio cache4 = new CacheMisterio();

                out.println("Criador: " + criador);
                cache4.setCriador(criador);

                out.println("Introduza as coordenadas (se for decimal separar por virgula):");
                out.print("\tLatitude: ");
                coord.setLatitude(input.nextDouble());
                out.print("\tLongitude: ");
                coord.setLongitude(input.nextDouble());
                cache4.setCoordenadas(coord);

                out.print("Introduza descrição:  ");
                descricao = input.next();
                cache4.setDescricao(descricao);

                out.print("Introduza dificuldade (1-5): ");
                dificuldade = input.nextInt();

                if (dificuldade >= 1 && dificuldade <= 5)
                    cache4.setDificuldade(dificuldade);
                else
                    throw new DificuldadeInvalidaException();

                out.print("Introduza enigma para a coordenada da latitude: ");
                cache4.setEnigmaLatitude(input.next());

                out.print("Introduza solução para o enigma: ");
                cache4.adicionarPuzzle(input.next(), coord.getLatitude());

                out.print("Introduza enigma para a coordenada da longitude: ");
                cache4.setEnigmaLongitude(input.next());

                out.print("Introduza solução para o enigma: ");
                cache4.adicionarPuzzle(input.next(), coord.getLongitude());

                caches.put(cache4.getCodigo(), cache4);
                out.println(cache4.getTipo() + " adicionada com sucesso!\n");
                f = false;
                break;

                case 5:
                CacheEvento cache5 = new CacheEvento();

                out.println("Criador: " + criador);
                cache5.setCriador(criador);

                out.println("Introduza as coordenadas (se for decimal separar por virgula):");
                out.print("\tLatitude: ");
                coord.setLatitude(input.nextDouble());
                out.print("\tLongitude: ");
                coord.setLongitude(input.nextDouble());
                cache5.setCoordenadas(coord);

                out.print("Introduza descrição: ");
                descricao = input.next();
                cache5.setDescricao(descricao);

                caches.put(cache5.getCodigo(), cache5);
                out.println(cache5.getTipo() + " adicionada com sucesso!\n");
                f = false;
                break;

                case 6:
                CacheVirtual cache6 = new CacheVirtual();

                out.println("Criador: " + criador);
                cache6.setCriador(criador);

                out.println("Introduza as coordenadas (se for decimal separar por virgula):");
                out.print("\tLatitude: ");
                coord.setLatitude(input.nextDouble());
                out.print("\tLongitude: ");
                coord.setLongitude(input.nextDouble());
                cache6.setCoordenadas(coord);

                out.print("Introduza descrição: ");
                descricao = input.next();
                cache6.setDescricao(descricao);

                caches.put(cache6.getCodigo(), cache6);
                out.println(cache6.getTipo() + " adicionada com sucesso!\n");
                f = false;
                break;

                case 7:
                f = false;
                break;

                default:
                throw new SelecaoInvalidaException();
            }
        }
    }

    /**
     * A função removerCache remove uma cache.
     */
    public void removerCache() throws PrivilegiosInsuficientesException,CacheNaoExisteException {
        for (int i = 0; i < 100; i++) out.println();
        String codigo;

        out.println("-----------GeocachingPOO: Remoção de cache---------");
        out.print("Introduza o codigo da cache a remover: ");
        codigo = input.next();

        if (isAdmin() || (currentUser.getNome().equals(caches.get(codigo).getCriador()))) {
            if (caches.get(codigo) == null)
                throw new CacheNaoExisteException();
            else {
                caches.remove(codigo); // se a cache existe, remove!
                if (reportedCaches.get(codigo) != null)
                    reportedCaches.remove(codigo); // se esta cache estava  reportada removemos tambem
                out.println("Removido com sucesso!");
            }
        }else{
            throw new PrivilegiosInsuficientesException();
        }
    }

    /**
     * A função reportarCache verifica se um Utilizador encontra uma anomalia na cache, e caso isso aconteça reporta a cache.
     */
    public void reportarCache() throws CacheNaoExisteException {
        for (int i = 0; i < 100; i++) out.println();
        String codigo2, motivo;

        out.println("-----------GeocachingPOO: Report abuse---------");
        out.print("Introduza o codigo da cache a reportar: ");
        codigo2 = input.next();

        if(caches.get(codigo2)==null) throw new CacheNaoExisteException();
        else{
            out.print("Introduza o motivo do report: ");
            motivo = input.next();

            caches.get(codigo2).setReportMotive(motivo);
            reportedCaches.put(codigo2, caches.get(codigo2));
            out.println("Reportado com sucesso!");
        }        
    }

    /**
     * A função listaAmigos verifica uma lista de amigos.
     */
    public void listaAmigos() {
        for (int i = 0; i < 100; i++) out.println();

        out.println("-----------GeocachingPOO: Lista de amigos---------");
        TreeMap<String, Utilizador> amigosTemp = currentUser.getAmigos();

        for (Map.Entry<String, Utilizador> entry : amigosTemp.entrySet()) {
            out.println("Amigo: " + entry.getValue().getNome());
            out.println("Email: " + entry.getValue().getEmail());
            out.println("---------------------------------");
        }
    }

    /**
     * A função pedidosAmizade verifica os pedidos de amizade de um Utilizador.
     */
    public void pedidosAmizade() {
        for (int i = 0; i < 100; i++) out.println();

        out.println("-----------GeocachingPOO: Pedidos de amizade---------");

        for (Map.Entry<String, Utilizador> entry : currentUser.getPedidos().entrySet()) {
            out.println("Pedido de amizade do user: " + entry.getValue().getNome());
            out.println("Email: " + entry.getValue().getEmail());
            out.println("---------------------------------");
        }
    }

    /**
     * A função pedidosAmizade verifica os pedidos de amizade de um Utilizador.
     */
    public void enviarPedidoAmizade() throws AmigoJaAdicionadoException,UtilizadorNaoExisteException, AdicionarMesmaPessoaException {
        for (int i = 0; i < 100; i++)out.println();
        String userAdd;

        out.println("-----------GeocachingPOO: Enviar pedido de amizade---------");
        out.print("Introduza o email do utilizador que pretende adicionar como amigo: ");
        userAdd = input.next();

        if (utilizadores.containsKey(userAdd)) {
            if (utilizadores.get(userAdd).getAmigos().get(currentUser.getEmail()) != null) {
                throw new AmigoJaAdicionadoException();
            } else if (currentUser.equals(utilizadores.get(userAdd))) {
                throw new AdicionarMesmaPessoaException();
            } else {
                utilizadores.get(userAdd).adicionarPedidoAmizade(currentUser);
                out.println("Pedido enviado com sucesso!");
            }
        } else {
            throw new UtilizadorNaoExisteException();
        }
    }

    /**
     * A função aceitarPedidoAmizade permite a um Utilizador aceitar um pedido de amizade.
     */
    public void aceitarPedidoAmizade() throws UtilizadorNaoExisteException, PedidoNaoExisteException{
        for (int i = 0; i < 100; i++) out.println();
        Utilizador u = new Utilizador();
        String userAccept;

        out.println("-----------GeocachingPOO: Aceitar pedido de amizade---------");
        out.print("Introduza o email do utilizador que pretende aceitar como amigo: ");
        userAccept = input.next();

        Utilizador temp = utilizadores.get(userAccept);

        if (utilizadores.containsValue(temp)) {
            if (currentUser.getPedidos().containsKey(userAccept)) {
                utilizadores.get(temp.getEmail()).adicionarAmigo(currentUser);
                currentUser.adicionarAmigo(temp);
                currentUser.rejeitarPedidoAmizade(userAccept);
                out.println("Pedido aceite com sucesso!");
            } else {
                throw new PedidoNaoExisteException();
            }
        } else {
            throw new UtilizadorNaoExisteException();
        }
    }

    /**
     * A função rejeitarPedidoAmizade permite a um Utilizador rejeitar um pedido de amizade.
     */
    public void rejeitarPedidoAmizade() throws PedidoNaoExisteException,UtilizadorNaoExisteException {
        for (int i = 0; i < 100; i++) out.println();
        String userRemove;

        out.println("-----------GeocachingPOO: Rejeitar pedido de amizade---------");
        out.print("Introduza o email do utilizador que pretende rejeitar: ");
        userRemove = input.next();

        if (utilizadores.containsKey(userRemove)) {
            if (currentUser.getPedidos().containsKey(userRemove)) {
                currentUser.rejeitarPedidoAmizade(userRemove);
                out.println("Pedido rejeitado com sucesso!");
            } else {
                throw new PedidoNaoExisteException();
            }
        } else {
            throw new UtilizadorNaoExisteException();
        }
    }

    /**
     * A função removerAmigo permite a um Utilizador remover um amigo da sua lista de amigos.
     */
    public void removerAmigo() throws UtilizadorNaoExisteException {
        for (int i = 0; i < 100; i++) out.println();
        String friendRemoval;

        out.println("-----------GeocachingPOO: Remover amigo---------");
        out.print("Introduza o email do amigo que pretende remover: ");
        friendRemoval = input.next();

        Utilizador u = utilizadores.get(friendRemoval);

        if (utilizadores.containsKey(friendRemoval) && currentUser.getAmigos().containsKey(friendRemoval)) {
            currentUser.removerAmigo(u);
            out.println("Amigo removido com sucesso!");
        }
    }

    /**
     * A função consultaAtividades permite ao Utilizador consultar a atividade de uma cache.
     */
    public void consultaAtividades() throws UtilizadorNaoExisteException,  AtividadeNullException {
        for (int i = 0; i < 100; i++) out.println();
        String user;
        int i = 0;

        out.println("-----------GeocachingPOO: Consulta de atividades---------");
        out.print("Email do utilizador a consultar: ");
        user = input.next();

        if (utilizadores.containsKey(user)) {
            Utilizador userTemp = utilizadores.get(user);
            if (userTemp.getAtividades().size() == 0) {
                throw new AtividadeNullException();
            } else {
                for (GregorianCalendar entry : userTemp.getAtividades().descendingKeySet()) {
                    if (i < 10) {
                        out.println("Cache nº"+ (userTemp.getAtividades().size() - i));
                        out.println("\tTipo Cache: " + userTemp.getAtividades().get(entry).getTipo());
                        out.println("\tCódigo Cache: " + userTemp.getAtividades().get(entry).getCodigo());
                        out.println("\tDescoberta em: " + entry.get(Calendar.DAY_OF_MONTH) + "/"
                            + entry.get(Calendar.MONTH) + "/"
                            + entry.get(Calendar.YEAR) + "\n");
                        i++;
                    }
                }
            }
        }else
            throw new UtilizadorNaoExisteException();
    }

    /**
     * A função estatisticasUser verifica as estatísticas referentes a um Utilizador.
     */
    public void estatisticasUser() throws UtilizadorNaoExisteException,AtividadeNullException,DataInvalidaException{
        for (int i = 0; i < 100; i++) out.println();
        String User;
        int normal = 0, micro = 0, evento = 0, virtual = 0, multi = 0, misterio = 0;
        int anoPesquisa, mesPesquisa = 0;

        out.println("-----------GeocachingPOO: Estatisticas---------");
        out.print("Introduza o email do utilizador que pretender verificar: ");
        User = input.next();

        if (utilizadores.containsKey(User)) {
            Utilizador UserTemp = utilizadores.get(User);
            if (UserTemp.getAtividades().size() == 0) {
                throw new AtividadeNullException();
            } else {
                out.print("Introduza o ano que pretende verificar (0 -> estatisticas globais): ");
                anoPesquisa = input.nextInt();
                if (String.valueOf(anoPesquisa).length() != 4 && anoPesquisa!=0) throw new DataInvalidaException();

                if (anoPesquisa != 0) {
                    out.print("Introduza o mês que pretende verificar (0 -> todos os meses desse ano): ");
                    mesPesquisa = input.nextInt();
                    if(mesPesquisa<0 || mesPesquisa>12 ) throw new DataInvalidaException();
                }
                if (anoPesquisa == 0) {
                    for (Map.Entry<GregorianCalendar, Cache> cach : UserTemp.getAtividades().entrySet()) {
                        if (cach.getValue().getTipo().equals("Cache Normal")) normal++;
                        if (cach.getValue().getTipo().equals("Micro Cache")) micro++;
                        if (cach.getValue().getTipo().equals("Cache Evento"))evento++;
                        if (cach.getValue().getTipo().equals("Cache Misterio")) misterio++;
                        if (cach.getValue().getTipo().equals("Cache Virtual"))virtual++;
                        if (cach.getValue().getTipo().equals("Multi Cache"))multi++;
                    }
                } else if (String.valueOf(anoPesquisa).length() == 4 && mesPesquisa >= 1 && mesPesquisa <= 12) {
                    for (Map.Entry<GregorianCalendar, Cache> cach : UserTemp.getAtividades().entrySet()) {
                        if (cach.getValue().getTipo().equals("Cache Normal") && cach.getKey().get(Calendar.MONTH) == mesPesquisa && cach.getKey().get(Calendar.YEAR) == anoPesquisa) normal++;
                        if (cach.getValue().getTipo().equals("Micro Cache") && cach.getKey().get(Calendar.MONTH) == mesPesquisa && cach.getKey().get(Calendar.YEAR) == anoPesquisa) micro++;
                        if (cach.getValue().getTipo().equals("Cache Evento")&& cach.getKey().get(Calendar.MONTH) == mesPesquisa && cach.getKey().get(Calendar.YEAR) == anoPesquisa)evento++;
                        if (cach.getValue().getTipo().equals("Cache Misterio") && cach.getKey().get(Calendar.MONTH) == mesPesquisa && cach.getKey().get(Calendar.YEAR) == anoPesquisa) misterio++;
                        if (cach.getValue().getTipo().equals("Cache Virtual") && cach.getKey().get(Calendar.MONTH) == mesPesquisa && cach.getKey().get(Calendar.YEAR) == anoPesquisa) virtual++;
                        if (cach.getValue().getTipo().equals("Multi Cache") && cach.getKey().get(Calendar.MONTH) == mesPesquisa&& cach.getKey().get(Calendar.YEAR) == anoPesquisa)multi++;
                    }
                } else if (String.valueOf(anoPesquisa).length() == 4 && mesPesquisa == 0) {
                    for (Map.Entry<GregorianCalendar, Cache> cach : UserTemp.getAtividades().entrySet()) {
                        if (cach.getValue().getTipo().equals("Cache Normal") && cach.getKey().get(Calendar.YEAR) == anoPesquisa)normal++;
                        if (cach.getValue().getTipo().equals("Micro Cache") && cach.getKey().get(Calendar.YEAR) == anoPesquisa)micro++;
                        if (cach.getValue().getTipo().equals("Cache Evento") && cach.getKey().get(Calendar.YEAR) == anoPesquisa)evento++;
                        if (cach.getValue().getTipo().equals("Cache Misterio") && cach.getKey().get(Calendar.YEAR) == anoPesquisa)misterio++;
                        if (cach.getValue().getTipo().equals("Cache Virtual") && cach.getKey().get(Calendar.YEAR) == anoPesquisa)virtual++;
                        if (cach.getValue().getTipo().equals("Multi Cache")&& cach.getKey().get(Calendar.YEAR) == anoPesquisa)multi++;
                    }
                }
                out.println("-------------------------");
                out.println("O utilizador encontrou o seguinte numero de caches nesse dado periodo:");
                out.println("\tCache Normal: " + normal);
                out.println("\tMicro Cache: " + micro);
                out.println("\tCache Evento: " + evento);
                out.println("\tCache Misterio: " + misterio);
                out.println("\tCache Virtual: " + virtual);
                out.println("\tMulti Cache: " + multi); 
                out.println("\nPara um total de " + UserTemp.getPontuacao() + " pontos"); 
            }
        } else {
            throw new UtilizadorNaoExisteException();
        }
    }

    /**
     * A função definicoesConta permite alterar as definições da conta de um Utilizador.
     */
    public void definicoesConta() throws PrivilegiosInsuficientesException, SelecaoInvalidaException,DataInvalidaException, UtilizadorNaoExisteException {
        for (int i = 0; i < 100; i++)out.println();
        Boolean v = true;

        out.println("-----------GeocachingPOO: Definições de conta---------");
        out.println("1 - Alterar password");
        out.println("2 - Alterar data nascimento");
        out.println("3 - Alterar morada");
        if (isAdmin())  out.println("4 - Alterar privilegios");
        out.println("5 - Exit");
        out.println("----------------------");
        out.print("Decisão: ");
        int decisao = input.nextInt();

        while (v) {
            switch (decisao) {
                case 1:
                out.println("\n-----------GeocachingPOO: Alterar password---------");
                out.print("Introduza password nova: ");
                currentUser.setPw(input.next());
                out.println("Sucesso!");
                out.println("----------------------\n");
                v = false;
                break;

                case 2:
                out.println("\n-----------GeocachingPOO: Alterar data de nascimento---------");
                String[] dataSplit;
                String data;
                int dia,mes,ano;

                out.print("Introduza data de nascimento nova: (Dia/Mes/Ano) ");
                data = input.next();

                dataSplit = data.split("/");

                try {
                    dia = Integer.parseInt(dataSplit[0]);
                    mes = Integer.parseInt(dataSplit[1]);
                    ano = Integer.parseInt(dataSplit[2]);
                } catch (Exception e) {
                    throw new DataInvalidaException();
                }

                if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && String.valueOf(ano).length() == 4) {
                    GregorianCalendar novaData = new GregorianCalendar(ano,mes, dia);
                    currentUser.setDate(novaData);
                    out.println("Sucesso!");
                } else {
                    throw new DataInvalidaException();
                }

                out.println("----------------------\n");
                v = false;
                break;

                case 3:
                out.println("\n-----------GeocachingPOO: Alterar morada---------");
                out.print("Introduza morada nova: ");
                currentUser.setMorada(input.next());
                out.println("Sucesso!");
                out.println("----------------------\n");
                v = false;
                break;

                case 4:
                if (isAdmin()) {
                    out.println("\n-----------GeocachingPOO: Alterar privilegios---------");
                    Utilizador t = new Utilizador();
                    out.print("Introduza o email do utilizador: ");
                    t = utilizadores.get(input.next());
                    if (t != null) {
                        out.print("Introduza o novo grau de privilegios do utilizador (0 normal/1 admin): ");
                        t.setPrivilegios(input.nextInt());
                        out.println("Sucesso!");
                    } else {
                        v = false;
                        throw new UtilizadorNaoExisteException();
                    }
                    out.println("----------------------\n");
                }else throw new PrivilegiosInsuficientesException();
                v = false;
                break;

                case 5:
                v = false;
                break;

                default:
                throw new SelecaoInvalidaException();
            }
        }
    }

    /**
     * A função listaUsers verifica a lista de Utilizadores.
     */
    public void listaUsers() {
        for (int i = 0; i < 100; i++) out.println();

        out.println("-----------GeocachingPOO: Lista de utilizadores---------");
        for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
            out.println(entry.getValue().toString(entry.getValue()));
        }
        out.println("----------------------------------------------------");
        out.println("Existem " + utilizadores.size() + " utilizadores no sistema GeocachingPOO");
        out.println("----------------------------------------------------");
    }

    /**
     * A função removeUser permite ao Utilizador decidir se quer ou não remover a sua conta.
     */
    public boolean removeUser() throws UtilizadorNaoExisteException, PrivilegiosInsuficientesException{
        for (int i = 0; i < 100; i++) out.println();
        char decisao;

        out.println("-----------GeocachingPOO: Apagar conta---------");
        if (!isAdmin()) {
            out.print("Tem a certeza que pretende remover a sua conta? (y/n) : ");
            decisao = input.next().charAt(0);

            if (decisao == ('y')) {
                utilizadores.remove(currentUser.getEmail());
                out.println("Conta removida com sucesso!\n");
                return true;
            } else {
                out.println("Abortado");
                return false;
            }
        } else {
            out.print("Introduza o email do utilizador que pretende remover: ");
            String userRemover = input.next();
            if (!utilizadores.containsKey(userRemover))
                throw new UtilizadorNaoExisteException();
            else {
                utilizadores.remove(userRemover);
                out.println("Conta removida com sucesso!\n");
                return true;
            }
        }
    }

    // EVENTOS!!
    /**
     * A função listaEventos verifica a lista dos eventos.
     */
    public void listaEventos() {
        for (int i = 0; i < 100; i++) out.println();

        out.println("-----------GeocachingPOO: Lista de eventos---------");
        for (Map.Entry<String, Evento> entry : eventos.entrySet()) {
            out.println(entry.getValue().toString(entry.getValue()));
        }
        out.println("----------------------------------------------------");
        out.println("Existem " + eventos.size() + " eventos no sistema GeocachingPOO");
        out.println("----------------------------------------------------");
    }

    /**
     * A função participarEvento permite ao Utilizador registar a sua participação num evento.
     */
    public void participarEvento() throws EventoNaoExisteException,DataInvalidaException, JaInscritoEventoException,InscricoesCheiasException, PassouDataLimiteException {
        for (int i = 0; i < 100; i++)out.println();

        out.println("-----------GeocachingPOO: Participar num evento---------");
        out.print("Introduza o código do evento que deseja participar: ");
        String codigoIntroduzido = input.next(), data;
        String dataSplit[];
        char confirmacao;
        int dia, mes, ano;
        Boolean podeRegistar = false;

        Evento aux = eventos.get(codigoIntroduzido);
        if (aux == null) 
            throw new EventoNaoExisteException();

        out.println(aux.toString(aux));
        out.println("---------------------------------------------");
        out.print("É este o evento pretendido? (y/n) : ");
        confirmacao = input.next().charAt(0);

        if (confirmacao == 'y') {
            out.print("Introduza a data de hoje (Dia/Mês/Ano): ");
            data = input.next();
            dataSplit = data.split("/");
            try {
                dia = Integer.parseInt(dataSplit[0]);
                mes = Integer.parseInt(dataSplit[1]);
                ano = Integer.parseInt(dataSplit[2]);
            } catch (Exception e) {
                throw new DataInvalidaException();
            }
            if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12
            && String.valueOf(ano).length() == 4) {
                GregorianCalendar d = new GregorianCalendar(ano, mes, dia);
                if (d.compareTo(aux.getData()) <= 0)
                    podeRegistar = true;
            }
            if (podeRegistar) {
                if (aux.getNumInscricoes() <= aux.getMaximoInscricoes()) {
                    if (aux.getParticipantes().containsKey(currentUser.getEmail()))
                        throw new JaInscritoEventoException();
                    else {
                        aux.addParticipante(currentUser);
                        out.println("Participação registada com sucesso!");
                    }
                } else
                    throw new InscricoesCheiasException();
            } else {
                throw new PassouDataLimiteException();
            }
        }
    }

    /**
     * A função adicionarEvento adiciona um evento.
     */
    public void adicionarEvento() throws PrivilegiosInsuficientesException,DataInvalidaException, CacheNaoExisteException,CacheJaInseridaException {
        for (int i = 0; i < 100; i++)out.println();

        Evento ev = new Evento();
        Metereologia m = new Metereologia();
        String data, verificacao, codigo;
        String dataSplit[];
        int dia, mes, ano, c = 1;
        double lat1, lon1;

        out.println("-----------GeocachingPOO: Adicionar Evento---------");
        if (!isAdmin())
            throw new PrivilegiosInsuficientesException();

        out.println("Criador: " + currentUser.getNome());
        ev.setCriador(currentUser.getNome());

        out.print("Introduza o nome do evento: ");
        ev.setNomeEvento(input.next());

        out.println("Introduza as coordenadas (se for decimal separar por virgula):");
        out.print("\tLatitude: ");
        lat1 = input.nextDouble();
        out.print("\tLongitude: ");
        lon1 = input.nextDouble();

        out.print("Introduza o número de inscrições: ");
        ev.setMaxInscricoes(input.nextInt());

        out.print("Introduza a data limite das inscrições: (Dia/Mês/Ano) ");
        data = input.next();
        dataSplit = data.split("/");

        try {
            dia = Integer.parseInt(dataSplit[0]);
            mes = Integer.parseInt(dataSplit[1]);
            ano = Integer.parseInt(dataSplit[2]);
        } catch (Exception e) {
            throw new DataInvalidaException();
        }

        if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && String.valueOf(ano).length() == 4) {
            GregorianCalendar d = new GregorianCalendar(ano, mes, dia);
            ev.setData(d);
        } else {
            throw new DataInvalidaException();
        }

        out.println("Introduza o código das caches que pretende introduzir no seu evento.");
        for (Map.Entry<String, Cache> entry : caches.entrySet()) {
            out.println("Tipo de cache: " + entry.getValue().getTipo() + " Código: " + entry.getValue().getCodigo() 
                + " Distância: "+ entry.getValue().getCoordenadas().DistanciaKM(lat1,lon1,entry.getValue().getCoordenadas().getLatitude(),entry.getValue().getCoordenadas().getLongitude())+ "km");
        }

        while (true) {
            out.print("\tIntroduza o código: ");
            codigo = input.next();
            ev.adicionarCache(codigo, caches.get(codigo));
            if (caches.size() <= 1)
                break;
            if (caches.size() == ev.getCaches().size())
                break;
            out.print("Introduza 0 para finalizar inserção de caches, 1 para continuar: ");
            if (input.nextInt() == 0)
                break;
        }

        eventos.put(ev.getCodigoEvento(), ev);
        out.println("Evento adicionado com sucesso!\n");
    }

    /**
     * A função removerEvento remove um evento.
     */
    public void removerEvento() throws PrivilegiosInsuficientesException,EventoNaoExisteException {
        for (int i = 0; i < 100; i++) out.println();
        String codigo;

        out.println("-----------GeocachingPOO: Remoção de evento---------");
        out.print("Introduza o codigo do evento a remover: ");
        codigo = input.next();

        if (isAdmin()) {
            if (eventos.get(codigo) == null)
                throw new EventoNaoExisteException();
            else {
                eventos.remove(codigo);
                out.println("Removido com sucesso!");
            }
        } else {
            throw new PrivilegiosInsuficientesException();
        }
    }

    /**
     * A função simularEvento permite a simulação de um evento.
     */
    public void simularEvento() throws PrivilegiosInsuficientesException,EventoNaoExisteException,NaoExistemParticipantesException {
        for (int i = 0; i < 100; i++)out.println();

        Boolean flag = true;
        GregorianCalendar start;
        TreeMap<String, Cache> listaCaches;
        TreeMap<String, Utilizador> listaParticipantes;
        Metereologia meteo = new Metereologia();
        String codigo, vencedor = new String();
        Random random = new Random();
        int pmax = 0, tempo;

        out.println("-----------GeocachingPOO: Simulação de evento---------");
        if(!isAdmin())
            throw new PrivilegiosInsuficientesException();
        out.print("Introduza o codigo do evento a simular: ");

        codigo = input.next();
        Evento ev = eventos.get(codigo);
        tempo = randomEntre(1, 9);
        if (ev == null)
            throw new EventoNaoExisteException();
        if (ev.getParticipantes().size() == 0)
            throw new NaoExistemParticipantesException();

        start = (GregorianCalendar) eventos.get(codigo).getData().clone();
        start.add(Calendar.DAY_OF_MONTH, 1); // o evento comeca um dia depois da data limite!

        listaCaches = eventos.get(codigo).getCaches();
        listaParticipantes = eventos.get(codigo).getParticipantes();

        List<String> keysCaches = new ArrayList<String>(listaCaches.keySet()); // converte as keys num arraylist para ser mais facil gerar um nº random
        List<String> keysParticipantes = new ArrayList<String>(listaParticipantes.keySet()); // converte as keys num arraylist para ser mais facil gerar um nº random
        List<String> listaTempo = new ArrayList<String>(meteo.getMetereologia().keySet()); // converte as keys num arraylist para ser mais facil gerar um nº random

        out.println("Condições metereológicas: " + listaTempo.get(tempo) + ",sendo atribuido " + meteo.getPontos(listaTempo.get(tempo)) + " pontos extra.");

        while (listaCaches.size() != 0) {
            String randomKeyCache = keysCaches.get(random.nextInt(keysCaches.size()));
            String randomKeyUser = keysParticipantes.get(random.nextInt(keysParticipantes.size()));
            Utilizador userLista = listaParticipantes.get(randomKeyUser);
            Utilizador userMap = utilizadores.get(userLista.getEmail());
            
            if (listaCaches.get(randomKeyCache) != null) {
                int randomMinutos = randomEntre(1, 60);
                int randomHoras = randomEntre(0, (24/keysCaches.size())); //se existem 2 caches, o relogio so pode avancar no maximo entre 0 e 12hrs (24/2)

                start.add(Calendar.HOUR_OF_DAY, randomHoras);
                start.add(Calendar.MINUTE, randomMinutos);

                out.println("No dia " + start.get(Calendar.DAY_OF_MONTH) + "/"  + start.get(Calendar.MONTH) + "/"+ start.get(Calendar.YEAR) 
                    + " às " + start.get(Calendar.HOUR_OF_DAY) + "h e "+ start.get(Calendar.MINUTE)+ "m, foi descoberta uma cache do tipo "
                    + ev.getTipo(randomKeyCache)+ " pelo participante "+ userMap.getNome());

                userMap.addPontuacao(userMap.getPontuacao()+ listaCaches.get(randomKeyCache).getPontuacao()+ (listaCaches.get(randomKeyCache).getDificuldade() / 2)+ tempo);
                listaCaches.remove(randomKeyCache);
            }
        }

        out.println("\nConcluimos a simulação, obtendo os seguintes resultados!");

        for (Map.Entry<String, Utilizador> entry : listaParticipantes.entrySet()) {
            out.println("O participante " + entry.getValue().getNome()+ " fez " + entry.getValue().getPontuacao() + " pontos!");
            if (entry.getValue().getPontuacao() > pmax) {
                pmax = entry.getValue().getPontuacao();
                vencedor = entry.getValue().getNome();
            }
        }

        out.println("\nSendo o vencedor deste evento, o participante " + vencedor + " com " + pmax + " pontos!\n");

        out.println("---------Obrigado por participarem neste evento----------");
        out.print("Deseja remover este evento, visto que já foi simulado? (y/n) : ");
        if(input.next().charAt(0)=='y'){
            eventos.remove(codigo);
            out.println("Evento removido com sucesso!");
        }
    }

    /**
     * A função randomEntre gera um número aleatório entre dois inteiros para utilizar no evento.
     * @param i Inteiro inferior.
     * @param f Inteiro superior.
     */
    public int randomEntre(int i, int f) {
        return i + (int) Math.round(Math.random() * (f - i));
    }

    /**
     * A função isAdmin verifica se um utilizador tem privilegios de administrador
     */
    public boolean isAdmin(){
        return currentUser.getPrivilegios()==1;
    }
}