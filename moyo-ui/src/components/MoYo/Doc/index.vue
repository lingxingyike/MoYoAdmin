<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'MoYoDoc',
  data() {
    return {
      url: 'http://doc.moyo.vip/moyo-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>