export default {
  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'Food Friends',
    htmlAttrs: {
      lang: 'en',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/tailwindcss
    '@nuxtjs/tailwindcss',
  ],

  tailwindcss: {
    configPath: '~/tailwind.config.js'
  },

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    '@nuxtjs/auth-next'
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
    // Workaround to avoid enforcing hard-coded localhost:3000: https://github.com/nuxt-community/axios-module/issues/308
    baseURL: '/',
    
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {},

  auth: {
    strategies: {
      local: {
        token: {
          property: 'data.userId',
          global: true,
          // type: 'Bearer'
        },
        user: {
          property: 'data',
          autoFetch: false
        },
        endpoints: {
          login: { url: 'http://localhost:8080/user/login', method: 'post' },
          register: { url: 'http://localhost:8080/user/register', method: 'post' },
          logout: { url: 'http://localhost:8080/user/logout', method: 'post' }
        },
        // autoLogout: false
      }
    },
    redirect: {
      login: '/Unauthorized',
      home: '/'
    }
  },

  vue: {
    config: {
      productionTip: false,
      devtools: true
    }
  },

  env: {
    baseUrl: process.env.BASE_URL,
    apiPort: process.env.API_PORT
  }
}
