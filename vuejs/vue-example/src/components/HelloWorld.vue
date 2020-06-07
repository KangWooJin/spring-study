<template>
  <div>
    <div>@Props : {{ msg }}</div>
    <div>computed : {{ message }}</div>
    <div>method : {{ getMessage() }}</div>
  </div>
</template>

<script lang="ts">
  import {Component, Prop, Vue, Watch} from "vue-property-decorator";

  @Component
  export default class HelloWorld extends Vue {
    @Prop() private msg!: string;
    private name = "woojin";

    // computed
    get message(): string {
      return this.msg;
    }

    // method
    private getMessage(): string {
      return this.msg;
    }

    @Watch("name", {immediate: true})
    private onChange(newVal: string): string {
      return newVal;
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped></style>
