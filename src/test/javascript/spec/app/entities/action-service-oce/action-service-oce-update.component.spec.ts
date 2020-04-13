import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { OpenCivicEngagementTestModule } from '../../../test.module';
import { ActionServiceOceUpdateComponent } from 'app/entities/action-service-oce/action-service-oce-update.component';
import { ActionServiceOceService } from 'app/entities/action-service-oce/action-service-oce.service';
import { ActionServiceOce } from 'app/shared/model/action-service-oce.model';

describe('Component Tests', () => {
  describe('ActionServiceOce Management Update Component', () => {
    let comp: ActionServiceOceUpdateComponent;
    let fixture: ComponentFixture<ActionServiceOceUpdateComponent>;
    let service: ActionServiceOceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [OpenCivicEngagementTestModule],
        declarations: [ActionServiceOceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ActionServiceOceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ActionServiceOceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ActionServiceOceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ActionServiceOce(123);
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
        const entity = new ActionServiceOce();
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
