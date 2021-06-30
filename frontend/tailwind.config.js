module.exports = {
  purge: [],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      spacing: {
        '100': '25rem',
        '104': '26rem',
        '108': '27rem',
        '112': '28rem',
        '120': '30rem',
        '140': '35rem',
        '160': '40rem',
        '172': '43rem',
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/line-clamp'),
  ],
}
