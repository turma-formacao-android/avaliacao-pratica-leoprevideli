package br.com.cast.turmaformacao.avaliacaopratica.model.entities;

public enum SocialNetworkEnum {

    FACEBOOK("Facebook"),
    GOOGLEPLUS("Google+"),
    TWITTER("Twitter");

    private final String socialNetwork;

    private SocialNetworkEnum(String socialNetwork){
        this.socialNetwork = socialNetwork;
    }

    public static SocialNetworkEnum getInstance(String socialNetwork) {
        SocialNetworkEnum[] values = values();
        for (SocialNetworkEnum sn : values) {
            if (sn.socialNetwork.equals(socialNetwork)) {
                return sn;
            }
        }
        return null;
    }
}
