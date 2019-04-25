package myhealth.ufscar.br.myhealth;

public enum SignUpSteps {
    ACCESS_DATA("Dados de Acesso",1),
    PERSONAL_DATA("Dados Pessoas", 2),
    ADDRESS("Endereço", 3),
    REGISTER_SUCCESS("Cadastro realizado com sucesso", 4),
    HEALTH_DATA("Informações de saúde", 5),
    MONITORING_SETTINGS("Configurações de monitoramento",6),
    SETTINGS_SUCCESS("Monitoramento configurado com sucesso", 7);

    String stepTitle;
    Integer step;

    SignUpSteps(String stepTitle, Integer step) {
        this.stepTitle = stepTitle;
        this.step = step;
    }
}
