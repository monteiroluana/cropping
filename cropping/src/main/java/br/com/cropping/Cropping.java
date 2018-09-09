package br.com.cropping;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Cropping", urlPatterns = {"/Cropping"})
public class Cropping extends HttpServlet {

    private static String nome;
    private static String msg;

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        Cropping.msg = msg;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Cropping.nome = nome;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Recebendo o nome da imagem
            setNome(request.getParameter("file"));

            //Diretório onde a imagem deve estar armazenada
            String dir = "C:\\temp";
            File diretorio = new File(dir);

            //chamando a função procurar Imagem
            procurarImagem(diretorio);

            request.setAttribute("msg", getMsg());
            RequestDispatcher dispatcher = request.getRequestDispatcher("desafio.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    //Função procurar imagem
    private static void procurarImagem(File diretorio) throws IOException {
        String[] arquivos = diretorio.list();

        for (int i = 0; i < arquivos.length; i++) {

            String string = arquivos[i];

            File diretorioRaiz = new File(diretorio.getAbsolutePath() + "\\" + string);

            if (diretorioRaiz.isDirectory()) {
                procurarImagem(diretorioRaiz);

            } else if (diretorioRaiz.isFile()) {

                String nameFile = diretorioRaiz.getName();
                int fileSize = nameFile.length();

                String extensao = nameFile.substring(fileSize - 4, fileSize);

                //Definindo a extenção dos arquivos que serão listados
                if (extensao.compareToIgnoreCase(".png") == 0) {

                    System.out.println("nameFile> " + nameFile);

                    //Verificando se o arquivo existe na pasta C:/Temp
                    if (nameFile.equals(getNome())) {
                        System.out.println("arquivo existente");
                        //Buscando a imagem 
                        BufferedImage originalImgage = ImageIO.read(new File("C:/temp/" + getNome()));

                        //Verificando se a imagem respeita o tamanho minimo de 300x300
                        if (originalImgage.getWidth() >= 300 && originalImgage.getHeight() >= 300) {
                            System.out.println("tamanho compativel");
                            //Achando o centro da imagem
                            int w = originalImgage.getWidth() / 2;
                            int h = originalImgage.getHeight() / 2;

                            //recortando  ponto inicial da largura, ponto inicial da altura, largura do corte, altura do corte
                            BufferedImage SubImgage = originalImgage.getSubimage((w - 50), (h - 50), 100, 100);
                            //System.out.println("Cropped Image Dimension: " + SubImgage.getWidth() + "x" + SubImgage.getHeight());

                            File outputfile = new File("C:/temp/desafioSciCrop.jpg");
                            ImageIO.write(SubImgage, "jpg", outputfile);
                            System.out.println("Image cropped successfully: " + outputfile.getPath());

                            setMsg("<script>alert('Imagem recortada com Sucesso!');</script>");

                            //Se o tamanho não for respeitado
                        } else {
                            setMsg("<script>alert('A imagem não respeita o tamanho mínimo de 300x300');</script>");

                        }

                        //Se não achar a imagem na pasta
                    } else {
                        setMsg("<script>alert('Imagem não encontrada OU extensão incompátivel');</script>");

                    }
                }
            }
        }
    }

}
