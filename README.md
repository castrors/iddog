# iddog


## Requisitos de negócio:

* Criar uma tela de login onde o usuário precisa digitar um endereço de email válido.
* Armazene localmente o token retornado pela API no login.
* Após o login feito com sucesso, redirecione para uma tela onde deve conter as listas com as imagens
* Crie uma forma para que o usuário possa navegar entre as quatro raças de cachorros (`husky`, `labrador`, `hound` e `pug`)
* Ao clicar em uma imagem, ela deve ser exibida de forma expandida.

[doc da API](https://github.com/idwall/desafios-iddog)

## Requisitos técnicos

* Fazer cache das imagens
* Versão mínima: Android API 16 ou iOS 9
* É recomendado o uso de libs de terceiros para:
  * chamadas de rede
  * download e cache das imagens
* O uso de todas as libs deve ser justificado no `README`.
* Faça um `README` documentando tópicos que julga importante para quem for avaliar, tais como arquitetura, libs, decisões, como executar o projeto, etc.
* Caso tenha desenvolvido para Android, disponibilize o `.apk`.

## Arquitetura

A arquitetura adotada foi inspirada na [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) do Uncle Bob. Nela divide-se o projeto em 3 camadas bem desacopladas: A camada de dados, de domínio e de apresentação. A primeira é responsável por prover os dados para a aplicação. A segunda é responsável por manter as regras de negócio da aplicação. E a última, não menos importante, é responsável por apresentar os dados para o usuário.

## Bibliotecas utilizadas

Kotlin, que tem várias features interessantes como a sua interoperabilidade com Java, ser concisa, segura(?: !!), e com um ferramental incrível para o desenvolvedor. 

    //Kotlin dependencies
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines_version"

As dependências específicas do Android foi a adoção do novo esquema de pacotes do Android X, que faz parte do guarda-chuva de ferramentas recomendadas pelo Google, o Jetpack.

    //Android dependencies
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.core:core-ktx:1.0.2'
    implementation 'androidx.recyclerview:recyclerview:1.0.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    api "com.google.android.material:material:1.0.0"

A biblioteca de comunicação de redes utilizada foi o Retrofit juntamente com o OkHttp. Elas realmente facilitam a vida do desenvolvedor na hora de fazer requisições, e uma das últimas features do Retrofit que foi adotada neste projeto é utilizar coroutines por baixo dos panos, facilitando o tratamento das respostas, tornando opcional a utilização do jeito antigo com Callables.

    //Network dependencies
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    implementation "com.squareup.okhttp3:okhttp:3.14.2"
    implementation "com.squareup.okhttp3:logging-interceptor:3.14.2"

Utilizei o Picasso para tratar o loading das imagens no projeto. Com apenas uma linha de código, já é possivel ter o carregamento assincrono das imagens no projeto.

    //Image request dependency
    implementation 'com.squareup.picasso:picasso:2.71828'

Foi adotado também a ferramenta de injeção de dependências a Koin, que utiliza bastante da elegância do Kotlin para ajudar o desenvolvedor na gestão e criação de suas depenências no projeto.

    //Dependency injection
    implementation 'org.koin:koin-android:2.0.1'
    implementation 'org.koin:koin-androidx-scope:2.0.1'
    implementation 'org.koin:koin-androidx-viewmodel:2.0.1'

Uma biblioteca simples de chave e valor adotada foi a Hawk, o diferencial dela é que ela também criptografa os dados, deixando o app mais seguro.

    //Secure local persistence dependency
    implementation "com.orhanobut:hawk:2.0.1"

Só para mostrar os logs de maneira mais amigável, foi adotada a lib Timber

    //Logging dependency
    implementation 'com.jakewharton.timber:timber:4.7.1'
