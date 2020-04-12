import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { OpenCivicEngagementTestModule } from '../../../test.module';
import { OrganisationOceUpdateComponent } from 'app/entities/organisation-oce/organisation-oce-update.component';
import { OrganisationOceService } from 'app/entities/organisation-oce/organisation-oce.service';
import { OrganisationOce } from 'app/shared/model/organisation-oce.model';

describe('Component Tests', () => {
  describe('OrganisationOce Management Update Component', () => {
    let comp: OrganisationOceUpdateComponent;
    let fixture: ComponentFixture<OrganisationOceUpdateComponent>;
    let service: OrganisationOceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [OpenCivicEngagementTestModule],
        declarations: [OrganisationOceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrganisationOceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganisationOceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganisationOceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrganisationOce(123);
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
        const entity = new OrganisationOce();
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
