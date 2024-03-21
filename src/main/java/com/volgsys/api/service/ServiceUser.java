package com.volgsys.api.service;

import com.volgsys.api.Repository.UsersRepository;
import com.volgsys.api.Repository.UsuarioAcesso;
import com.volgsys.api.model.ListUser;
import com.volgsys.api.model.dto.UserDTO;
import com.volgsys.api.model.enums.TipoUser;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
@Component
public class ServiceUser     {
    @Autowired
    UsersRepository repositoryConfig;

    @Autowired
    ServiceCEP serviceCEP;
    @Autowired
    ServiceNotification serviceNotification;


    public String  saveUser (UserDTO userDTO) throws Exception {
        UsuarioAcesso usuarioAcessoRepository = new ModelMapper().map(userDTO, UsuarioAcesso.class);
        if(userExistente(usuarioAcessoRepository.getCpfcnpj())) {throw new IllegalArgumentException( "Usuario ja cadastrado no sistema.");}

        //Validacoes
        if(!validaCep(usuarioAcessoRepository.getCep())) {throw new IllegalArgumentException( "Cep invalido ou no formato errado.");}
        if(!validaEmail(usuarioAcessoRepository.getEmail())) {throw new IllegalArgumentException( "email invalido ou no formato errado.");}
        if(!validarTelefone(usuarioAcessoRepository.getTelefone())) {throw new IllegalArgumentException( "email invalido ou no formato errado.");}

        TipoUser tipo = tipoPessoa(usuarioAcessoRepository.getCpfcnpj());
        switch (tipo) {
            case PF:
                if(!validateCPF(usuarioAcessoRepository.getCpfcnpj())) {throw new IllegalArgumentException( "CPF invalido ou no formato errado.");}
                usuarioAcessoRepository.setTipopessoa(TipoUser.PF.getCodigo());
                break;
            case PJ:
                if(!validaCNPJ(usuarioAcessoRepository.getCpfcnpj())) {throw new IllegalArgumentException( "CNPJ invalido ou no formato errado.");}
                usuarioAcessoRepository.setTipopessoa(TipoUser.PJ.getCodigo());
                break;
        }

        var endereco = serviceCEP.BuscaCep(usuarioAcessoRepository.getCep());
        usuarioAcessoRepository.setRua(endereco.getLogradouro());
        usuarioAcessoRepository.setCep(endereco.getCep());

        repositoryConfig.save(usuarioAcessoRepository);
        serviceNotification.senSMS(usuarioAcessoRepository.getTelefone(), usuarioAcessoRepository.getNome());
        serviceNotification.sendEmail(usuarioAcessoRepository.getEmail(), "Usuário criado!", "Usuario criado com sucesso. \nlogin:" + usuarioAcessoRepository.getEmail() + "\nsenha: " + usuarioAcessoRepository.getPassword());
        return "Usuario salvo com sucesso.";
    }

    public ListUser listUser (String cpfcnpj){
        var userexistente = repositoryConfig.findByCpfcnpj(cpfcnpj);
        if(!userexistente.isPresent()) {throw new IllegalArgumentException("CPF/CNPJ Nao possui cadastro no sistema: " + cpfcnpj);}
        ListUser usuarioAcessoRepository = new ModelMapper().map(userexistente.get(), ListUser.class);
        return usuarioAcessoRepository;
    }

    public boolean userExistente (String valida){
        var userexistente = repositoryConfig.findByCpfcnpj(valida);

        if(userexistente.isPresent()){
            return true;
        }
        return false;
    }

    public TipoUser tipoPessoa(String cpfcnpj) {
        // Remove caracteres não numéricos do CPF/CNPJ
        cpfcnpj = cpfcnpj.replaceAll("[^0-9]", "");

        // Verifica se o CPF/CNPJ tem um número de dígitos válido
        if (cpfcnpj.length() < 11 || cpfcnpj.length() > 14) {
            throw new IllegalArgumentException("CPF/CNPJ inválido: " + cpfcnpj);
        }

        // Converte para um número inteiro
        long cpfoucnpj = Long.parseLong(cpfcnpj);

        // Verifica se o número é um CPF ou CNPJ válido
        if (cpfcnpj.length() == 14) { // Se tem 14 dígitos, considera CNPJ
            return TipoUser.PJ;
        } else if (cpfcnpj.length() == 11) { // Se tem 11 dígitos, considera CPF
            return TipoUser.PF;
        } else {
            throw new IllegalArgumentException("CPF/CNPJ inválido: " + cpfcnpj);
        }
    }

    // Method to validate CPF
    public static boolean validateCPF(String cpf) {
        // Remove caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        boolean allDigitsEqual = true;
        for (int i = 1; i < 11 && allDigitsEqual; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allDigitsEqual = false;
            }
        }
        if (allDigitsEqual || cpf.equals("12345678909")) {
            return false;
        }

        // Converte os caracteres do CPF para inteiros
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * digits[i];
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit == 10 || firstDigit == 11) {
            firstDigit = 0;
        }
        if (digits[9] != firstDigit) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * digits[i];
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit == 10 || secondDigit == 11) {
            secondDigit = 0;
        }
        if (digits[10] != secondDigit) {
            return false;
        }

        return true;
    }

    // Method to validate CNPJ
    public static boolean validaCNPJ(String vrCNPJ) {
        String cnpj = vrCNPJ.replace(".", "").replace("/", "").replace("-", "");

        int[] digitos = new int[14];
        int[] soma = new int[2];
        int[] resultado = new int[2];
        boolean[] cnpjOk = new boolean[2];

        String ftmt = "6543298765432";

        for (int nrDig = 0; nrDig < 14; nrDig++) {
            digitos[nrDig] = Character.getNumericValue(cnpj.charAt(nrDig));
            if (nrDig <= 11) {
                soma[0] += digitos[nrDig] * Character.getNumericValue(ftmt.charAt(nrDig + 1));
            }
            if (nrDig <= 12) {
                soma[1] += digitos[nrDig] * Character.getNumericValue(ftmt.charAt(nrDig));
            }
        }

        for (int nrDig = 0; nrDig < 2; nrDig++) {
            resultado[nrDig] = soma[nrDig] % 11;
            if ((resultado[nrDig] == 0 || resultado[nrDig] == 1)) {
                cnpjOk[nrDig] = digitos[12 + nrDig] == 0;
            } else {
                cnpjOk[nrDig] = digitos[12 + nrDig] == (11 - resultado[nrDig]);
            }
        }

        return cnpjOk[0] && cnpjOk[1];
    }

    // Method to validate CEP
    public static boolean validaCep(String cep) {
        if (cep.length() == 8) {
            cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
        }
        return cep.matches("[0-9]{5}-[0-9]{3}");
    }

    // Method to validate Email
    public static boolean validaEmail(String email) {
        return email.matches("(?i)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public static boolean validarTelefone(String telefone) {
        // Remove caracteres não numéricos do telefone
        String numeroLimpo = telefone.replaceAll("[^0-9]", "");

        // Verifica se o número possui a quantidade mínima de dígitos
        if (numeroLimpo.length() < 10) {
            return false;
        }

        // Verifica se o número começa com 0 (código de seleção de operadora) ou 55 (código internacional)
        if (numeroLimpo.startsWith("0") || numeroLimpo.startsWith("55")) {
            return false;
        }

        // Verifica se o número possui um padrão válido (DDDCelular+XXXXXXXXX ou DDDTelefoneFixo+XXXXXXXXX)
        Pattern pattern = Pattern.compile("^(?:(?:\\+|00)?55(?:\\d{2})?|0?(?:(\\d{2})?))(9?\\d{8})$");
        return pattern.matcher(numeroLimpo).matches();
    }
}
