import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Genre } from '../../model/genre';
import { GenreService } from '../../services/genre.service';

@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.sass']
})
export class AddGenreComponent implements OnInit {

  @Input() refresh;
  genre: Genre = {name: "", systemGrade: 0};
  notFound = false;
  badRequest = false;
  unauthorized = false;
  constructor(public activeModal: NgbActiveModal,
              private service: GenreService) { }

  ngOnInit(): void {
  }

  add() {
    this.service.create(this.genre).subscribe(
      res => {
        this.refresh();
        this.activeModal.close();
        this.badRequest = false;
      },
      error => {
        if (error.status === 400) {
          this.badRequest = true;
        }
      }

    );
  }

}
