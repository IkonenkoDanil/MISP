from rest_framework.generics import CreateAPIView
from rest_framework.mixins import ListModelMixin, RetrieveModelMixin, CreateModelMixin, UpdateModelMixin
from rest_framework.viewsets import GenericViewSet
from .models import DayStats
from .serializers import NoteSerializer, DayStatsListSerializer, DayStatsDetailSerializer, DayStatsCreateSerializer


class NoteCreateView(CreateAPIView):
    serializer_class = NoteSerializer


class DayStatsViewSet(GenericViewSet, ListModelMixin, RetrieveModelMixin, CreateModelMixin, UpdateModelMixin):
    def get_queryset(self):
        return DayStats.objects.filter(user_id=self.request.user.id)

    def get_serializer_class(self):
        if self.action == 'list':
            return DayStatsListSerializer
        if self.action == 'create':
            return DayStatsCreateSerializer
        return DayStatsDetailSerializer
