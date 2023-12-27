function getIndex(list,id){
  for(let i=0;i<list.length;i++){
    if(list[i].id === id){
      return i;
    }
  }
  return -1;
}
var messagesApi = Vue.resource('/messages{/id}')

Vue.component('message-form',{
  props:['messages','messageAttr'],
  data: function(){
    return{
      text:''
      ,id:''
    }
  },
  watch:{
    messageAttr: function(newVal,oldVal){
      this.text = newVal.text;
      this.id = newVal.id;
    }

  },
  template:`
          <div>
            <input type="text" palaceholder="Введите сообщение" v-model="text" />
            <input type="button" value="Сохранить" @click="save"/>
          </div>
  `,
  methods:{
    save:function(){
      var message = {text: this.text};
      if(this.id){
        messagesApi.update({id:this.id},message).then(
          result=>result.json().then(
            data=>{
              let index = getIndex(this.messages,data.id);
              this.messages.splice(index,1,data);
              this.text = '';
            }
          )
        );
      }else{
        messagesApi.save({},message).then(
          result=>result.json().then(
            data=>{
              this.messages.push(data);
              this.text = '';
            }
          )
        );
      }
    }
  }
})

Vue.component('message-row',{
  props:['message','editMessage','messages'],
  template: `<div>
                  <i>{{message.id}}</i> {{message.text}}
                  <span>
                    <input type="button" value="Изменить" @click="edit" />
                    <input type="button" value="X" @click="del" />
                  </span>
            </div>`,
  methods:{
    edit:function(){
      this.editMessage(this.message)
    },
    del:function(){
      messagesApi.remove({id:this.message.id}).then(result=>{
        if(result.ok){
          this.messages.splice(this.messages.indexOf(this.message),1);
        }
      })
    }
  }
})

Vue.component('messages-list',{
  props:['messages'],
  data:function(){
    return{
      message:null
    }
  },
  template: `<div>
      <message-form :messages="messages" :messageAttr="message"/>
      <message-row v-for="message in messages" :key="message.id" :message="message" :editMessage="editMessage" :messages="messages"/>
    <div>`,
  created: function(){
    messagesApi.get().then(result =>
      result.json().then(data =>
        data.forEach(message => this.messages.push(message))
      )
    )
  },
  methods:{
    editMessage:function(message){
      this.message = message;
    }
  }
})

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
      messages: [
        
      ]
    }
  })