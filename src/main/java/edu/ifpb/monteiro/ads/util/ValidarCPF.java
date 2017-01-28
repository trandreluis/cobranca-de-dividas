package edu.ifpb.monteiro.ads.util;

/**
* Classe com método para validação de CPF
*
* Pode ser utilizada livremente e modificada para se integrar à sistemas mais complexos.
*
* @version 2.0 -> Adicão de método para pegar somente os numeros de um CPF formatado.
* @since   05/06/2003
*/
public class ValidarCPF {

   /** Realiza a validação do CPF.
    *
    * @param   strCPF número de CPF a ser validado
    * @return  true se o CPF é válido e false se não é válido
    */
   public static boolean isCPF (String strCpf ) {
      int     d1, d2;
      int     digito1, digito2, resto;
      int     digitoCPF;
      String  nDigResult;

      d1 = d2 = 0;
      digito1 = digito2 = resto = 0;

      for (int nCount = 1; nCount < strCpf.length() -1; nCount++) {
    	  
         digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

         //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
         d1 = d1 + ( 11 - nCount ) * digitoCPF;

         //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
         d2 = d2 + ( 12 - nCount ) * digitoCPF;
         
      };

      //Primeiro resto da divisão por 11.
      resto = (d1 % 11);

      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
      if (resto < 2){
    	  digito1 = 0;    	  
      }
      else {
    	  digito1 = 11 - resto;    	  
      }

      d2 += 2 * digito1;

      //Segundo resto da divisão por 11.
      resto = (d2 % 11);

      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
      if (resto < 2) {
    	  digito2 = 0;    	  
      }
      else {
    	  digito2 = 11 - resto;    	  
      }

      //Digito verificador do CPF que está sendo validado.
      String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

      //Concatenando o primeiro resto com o segundo.
      nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

      //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
      return nDigVerific.equals(nDigResult);
   }

   public static String pegarSomenteNumerosCPF(String formatadoCPF) {
	   
	   //Espera-se um CPF formatado como ex.: 046.241.938-06
	   
	   String numerosCPF = "";
	   
	   for(int i = 0; i< formatadoCPF.length(); i++) {
		   if(i != 3 && i != 7 && i != 11) {
			   numerosCPF += formatadoCPF.charAt(i);
		   }
	   }
	   
	   return numerosCPF;
	   
   }
   
//   Use este trecho para testar a classe
//   public static void main(String[] args) {
//      System.out.println(isCPF("04624193806"));//valido
//      System.out.println(isCPF("18874453672"));//invalido
//      System.out.println(pegarSomenteNumerosCPF("046.241.938-06"));//04624193806
//   }
   
}
