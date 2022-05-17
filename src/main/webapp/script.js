const $btnIniciarSesion= document.querySelector('.Singboton'),
    $btnRegistrase = document.querySelector('.Regboton'),
    $Registrarse = document.querySelector('.Registrarse'),
    $IniciarSesion = document.querySelector('.IniciarSesion');



document.addEventListener('click', e=>{
    if (e.target === $btnIniciarSesion || e.target === $btnRegistrase){
        $IniciarSesion.classList.toggle('active');
        $Registrarse.classList.toggle('active')
    }
})

