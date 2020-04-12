import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { CommentOceUpdateComponent } from 'app/entities/comment-oce/comment-oce-update.component';
import { CommentOceService } from 'app/entities/comment-oce/comment-oce.service';
import { CommentOce } from 'app/shared/model/comment-oce.model';

describe('Component Tests', () => {
  describe('CommentOce Management Update Component', () => {
    let comp: CommentOceUpdateComponent;
    let fixture: ComponentFixture<CommentOceUpdateComponent>;
    let service: CommentOceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [CommentOceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CommentOceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommentOceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommentOceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommentOce(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommentOce();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
