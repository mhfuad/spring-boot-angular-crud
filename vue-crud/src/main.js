import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueI18n from 'vue-i18n';

import en from './locales/en.json'
import bd from './locales/bd.json'

const i18n = VueI18n.createI18n({
    locale: 'en', 
    fallbackLocale: 'en', 
    messages:{
        en,
        bd
    }, 
})

const app = createApp(App)

app.use(router, i18n)

app.mount('#app')
