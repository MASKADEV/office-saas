import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {AuthenticationService} from "../../../service/authentication.service";
import {TeamService} from "../../../service/team.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './teamManagment.component.html',
  styleUrls: ['./teamManagment.component.scss']
})
export class TeamManagmentComponent implements OnDestroy, OnInit{

  teams : any = [];
  teamModal:any = {
    id:null,
    title: '',
    mode: 'add',
    show : false,
  };

  dropDownList : any;

  loading : boolean = false;

  private teamSubscribe : Subscription = new Subscription();
  constructor(private teamService: TeamService, private router : Router) {
  }

  addTeam() {
    this.teamModal.show = true;
    this.teamModal.mode = 'add';
  }

  updateTeam(team : any) {
    this.teamModal.mode = 'update';
    this.teamModal.title = team.name;
    this.teamModal.show = true;
    this.teamModal.id = team.id;
  }

  save(){
    this.loading = true;
    try {
      if(this.teamModal.task){
        let data = {
          payload: {
            title : this.teamModal.title,
            description : "description",
            taskStatus : "PENDING"
          },
          id : this.teamModal.id
        }
        this.teamSubscribe = this.teamService.addTask(data).subscribe(res=> {
          console.log(res)
        })
      } else if(this.teamModal.mode == 'add'){
        let data = {
          name : this.teamModal.title
        }
        this.teamSubscribe = this.teamService.addTeam(data).subscribe(res => {
          console.log(res)
        })
      }else {
        // return console.log(this.teamModal.id)
        let data = {
          payload : {
            name : this.teamModal.title
          },
          id : this.teamModal.id
        }
        this.teamSubscribe = this.teamService.updateTeam(data).subscribe(res => {
          console.log(res);
        })
      }
    }catch (e) {
    }finally {
      setTimeout(() => {
        this.getTeams();
      this.teamModal = {
        id:null,
        title: '',
        mode: 'add',
        show : false,
      }
      this.loading = false;
      }, 1000)
    }
  }

  addTask(team : any) {
    this.teamModal.task = 'new task';
    this.teamModal.id = team.id;
    this.teamModal.show = true;
  }

  removeTeam(team : any) {
    this.loading = true;
    try {
      this.teamSubscribe = this.teamService.deleteTeam({id : team.id}).subscribe();
    }catch (e) {
    }finally {
      setTimeout(() => {
        this.getTeams();
        this.loading = false;
      }, 1000)
    }
  }
  getTeams() {
    this.loading = true;
    this.teamService.getTeams().subscribe(res => {
      this.teams = []
      res.results.results.map((team : any) => {
        this.teams.push(team)
      })
    })
    this.loading = false;
  }

  ngOnDestroy() {
    this.teamSubscribe.unsubscribe();
  }

  ngOnInit() {
    this.getTeams();
  }
}
